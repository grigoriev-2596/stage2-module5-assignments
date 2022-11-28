package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import assignments.annotations.FullNameProcessorGeneratorAnnotation;
import assignments.annotations.ListIteratorAnnotation;
import assignments.annotations.ReadFullProcessorNameAnnotation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalProcessor {
    private StringBuilder processorName;
    private Long period = 10_000_000_000_000L;
    private StringBuilder processorVersion;
    private List<String> processorNameParts = new LinkedList<>();
    private Scanner informationScanner;
    private static Logger logger = Logger.getLogger(LocalProcessor.class.getName());

    public LocalProcessor(String processorName, Long period, String processorVersion,
                          Scanner informationScanner, LinkedList<String> processorNameParts) {
        this.processorName = new StringBuilder(Objects.requireNonNull(processorName, "processorName must not be null"));
        this.period = Objects.requireNonNull(period, "period must not be null");
        this.processorVersion = new StringBuilder(Objects.requireNonNull(processorVersion, "processorVersion must not be null"));
        this.processorNameParts = Objects.requireNonNull(processorNameParts, "processorNameParts must not be null");
        this.informationScanner = Objects.requireNonNull(informationScanner, "informationScanner must not be null");
    }

    public LocalProcessor() {
    }

    @ListIteratorAnnotation
    public void printList(List<String> listToIterate) {
        for(String s : listToIterate) {
            System.out.println(s.hashCode());
        }
    }

    @FullNameProcessorGeneratorAnnotation
    public String fullNameProcessorGenerator() {
        for(String s : processorNameParts) {
            processorName.append(s).append(' ');
        }
        return processorName.toString();
    }

    @ReadFullProcessorNameAnnotation
    public void readFullProcessorVersion(File file) {
        try {
            informationScanner = new Scanner(file);
            while (informationScanner.hasNext()) {
                processorVersion.append(informationScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "An exception was thrown", e);
        } finally {
            if (informationScanner != null) {
                informationScanner.close();
            }
        }
    }
}
