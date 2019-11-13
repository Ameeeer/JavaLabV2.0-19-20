package ru.javalab.downloader;

import org.apache.commons.io.FilenameUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DownloadImpl extends Thread implements DownloadInterface {

    public void download() {
        Scanner sc = new Scanner(System.in);
        int threadsCappacity = sc.nextInt();
        List<Thread> threads = new ArrayList<Thread>();
        final String[] strings = new String[threadsCappacity];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = String.valueOf(sc.next());
        }
        for (int i = 0; i < threadsCappacity; i++) {
            {
                System.out.println("Started");
                final int finalI = i;
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
//                            URL website = new URL("https://png.pngtree.com/png-clipart/20190515/original/pngtree-beautiful-hologram-water-color-frame-png-image_3643167.jpg");
                            URL website = new URL(strings[finalI]);
                            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                            FileOutputStream fos = new FileOutputStream(finalI + "" + FilenameUtils.getName(website.getPath()));
                            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                            System.out.println(finalI);
                            System.out.println(Thread.currentThread().getName());
                            fos.close();
                            rbc.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                threads.add(t);
            }
            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
