package org.ecoinformatics;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.rmi.server.ExportException;
import java.io.InputStream;

import gov.loc.repository.bagit.BagFactory;
import gov.loc.repository.bagit.Bag;
import gov.loc.repository.bagit.writer.impl.ZipWriter;

public class App {
    public static void main(String[] args) {
        // Create a data directory
        File dataDirectory = new File("data");
        dataDirectory.mkdir();
        File subFolder = new File(dataDirectory.getAbsolutePath() + "/" + "subFolder");
        subFolder.mkdir();
        File dataFile = new File(subFolder.getAbsolutePath() + "/" + "myDataFile.csv");
        String str = "World";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile.getAbsolutePath(), true));
            writer.append(' ');
            writer.append(str);
            writer.close();
        } catch (Exception e) {
        }
        InputStream bagInputStream = null;
        BagFactory bagFactory = new BagFactory();
        Bag bag = bagFactory.createBag();
        bag.addFileToPayload(dataDirectory);
        bag = bag.makeComplete();
        String zipName = "./data/bag";

        File bagFile = new File("tempDir", zipName + ".zip");
        bag.setFile(bagFile);
        ZipWriter zipWriter = new ZipWriter(bagFactory);
        bag.write(zipWriter, bagFile);

        System.out.println(bagFile);
    }
}
