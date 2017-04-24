package com.music.animator;

import java.io.*;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioFormat.*;
import java.nio.*;
import org.jtransforms.fft.DoubleFFT_1D;

public class BeatDetector
{
        public static double[] ReadFile(File file) throws UnsupportedAudioFileException, IOException,InterruptedException
        {
            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            AudioFormat format = in.getFormat();
            //System.out.println(format);


            byte [] audio;

            try
            {
                if(format.getEncoding() != Encoding.PCM_SIGNED)
                {
                    throw new UnsupportedAudioFileException();

                }

                audio = new byte[in.available()];
                in.read(audio);
            }
            finally
            {
                in.close();
            }

            int bits = format.getSampleSizeInBits();
            System.out.println(bits);
            System.out.println(format.getSampleRate());
            double max = Math.pow(2,bits-1);

            // System.out.println((audio.length * 8));
            //  System.out.println(((audio.length * 8) / bits));
            ///Math.ceil(Math.log10(audio.length * 8 / bits)/Math.log10(2));
            double basetwo = Math.ceil(Math.log10(audio.length * 8 / bits)/Math.log10(2));

            int convert = (int) basetwo;
            Math.pow(2,convert);
            //System.out.print((int)Math.pow(2,convert-1));


            ByteBuffer buffer = ByteBuffer.wrap(audio);
            buffer.order(format.isBigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
            double[] samples = new double[(int)Math.pow(2,convert-1)];
            // convert sample-by-sample to a scale of
            // -1.0 <= samples[i] < 1.0
            for(int i = 0; i < samples.length; i++)
            {
                switch(bits)
                {
                    case 8:  samples[i] = ( buffer.get()      / max );
                        break;
                    case 16: samples[i] = ( buffer.getShort() / max );
                        break;
                    case 32: samples[i] = ( buffer.getInt()   / max );
                        break;
                    case 64: samples[i] = ( buffer.getLong()  / max );
                        break;
                    default: throw new UnsupportedAudioFileException();
                }
            }
            DoubleFFT_1D fft = new DoubleFFT_1D(samples.length);
            double[] fftData = new double[samples.length * 2];
            for (int i = 0; i < samples.length; i++) {
                // copying audio data to the fft data buffer, imaginary part is 0
                fftData[2 * i] = audio[i];
                fftData[2 * i + 1] = 0;
            }

            // calculating the fft of the data, so we will have spectral power of each frequency component
            // fft resolution (number of bins) is samplesNum, because we initialized with that value
            fft.complexForward(fftData);



            //FFTbase testing = new FFTbase();
            //double [] trash = testing.fft(samples,imagPart,true);
            double [] freq = new double[fftData.length];

            for(int i = 0; i < freq.length; i++)
            {
                freq[i] = fftData[i]* format.getFrameRate() / format.getFrameSize();
                System.out.println(freq[i]);
            }


            return freq;
        }



}
