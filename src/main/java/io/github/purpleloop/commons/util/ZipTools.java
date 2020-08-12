package io.github.purpleloop.commons.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/** Utility class for zip compression. */
public final class ZipTools {

    /** Private constructor. */
    private ZipTools() {
    }

    /**
     * Provides the inputStream for a given entry of a zip file.
     * 
     * @param archive the AIP archive
     * @param entryName the name of the entry to obtain
     * @return input stream for the entryName
     * @throws IOException in case of problem (for instance, entry not found)
     */
    public static InputStream getEntryInputStream(ZipFile archive, String entryName) throws IOException {

        Enumeration<? extends ZipEntry> zipEntries = archive.entries();
        while (zipEntries.hasMoreElements()) {
            ZipEntry zipEntry = zipEntries.nextElement();
            String zipEntryName = zipEntry.getName();
            if (zipEntryName.equalsIgnoreCase(entryName)) {
                return archive.getInputStream(zipEntry);
            }
        }
        
        throw new FileNotFoundException("Requested zip entry '" + entryName + "' couldn't de found in archive '"
                + archive.getName() + "'.");
    }

}
