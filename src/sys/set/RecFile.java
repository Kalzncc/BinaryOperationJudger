package sys.set;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class RecFile extends BinaryOperationFiles {
    String type ;
    public RecFile(String Dir, String type) throws FileNotFoundException {
        super(Dir, type);
        this.type = type;
    }
    public void write (ArrayList<AnsweringData> dataset) throws IOException {
        if (type != WRITE) throw new RuntimeException("Type exception");
        for (AnsweringData x :  dataset) {
            fout.writeUTF(x.toString());
        }
    }
    public ArrayList<AnsweringData> read() {
        if (type != READ) throw  new RuntimeException("Type exception");
        ArrayList<AnsweringData> dataset = new ArrayList<AnsweringData>();
        try {
            String te;
            while (true) {
                te = fin.readUTF();
                dataset.add(new AnsweringData(te));
            }
        }
        catch (Exception e) {
            return dataset;
        }
    }
    public void AddData(AnsweringData data) throws IOException {
        if (type != APPEND) throw  new RuntimeException("Type exception");
        System.out.println(data.date.getYear());
        fout.writeUTF(data.toString());
    }
}
