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
    private static Logger logger = Logger.getLogger(LocalProcessor.class.getName());

    public LocalProcessor(StringBuilder processorName, Long period, StringBuilder processorVersion, LinkedList<String> processorNameParts) {
        this.processorName = Objects.requireNonNull(processorName, "processorName must not be null");
        this.period = Objects.requireNonNull(period, "period must not be null");
        this. processorVersion = Objects.requireNonNull(processorVersion, "processorVersion must not be null");
        this.processorNameParts = Objects.requireNonNull(processorNameParts, "stringArrayList must not be null");
    }

    public LocalProcessor() {
    }

    @ListIteratorAnnotation
    public void printProcessorNameParts() {
        for(String s : processorNameParts) {
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
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                processorVersion.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "An exception was thrown", e);
        }
    }
}
