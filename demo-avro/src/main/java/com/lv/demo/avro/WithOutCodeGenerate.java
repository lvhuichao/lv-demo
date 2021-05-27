package com.lv.demo.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 不用生成example.avro.User即可使用。
 * https://avro.apache.org/docs/current/gettingstartedjava.html
 *
 * @desc:
 * @author: huichaolv@tencent.com
 * @create: 2021/5/27
 */
public class WithOutCodeGenerate {

    private static final String user_path = "/Users/lvhuichao/temp/user.avro";
    private static final String schema_path = "/Users/lvhuichao/gits/lv-demo/demo-avro/src/main/avro/user.avsc";

    private static final Schema schema;

    static {
        try {
            //parse schema
            schema = new Schema.Parser().parse(new File(schema_path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        new File(user_path).deleteOnExit();
        write();
        read();
    }

    private static void read() throws IOException {
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
        try (DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(new File(user_path), datumReader)) {
            while (dataFileReader.hasNext()) {
                System.out.println(dataFileReader.next());
            }
        }
    }

    private static void write() throws IOException {
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            List<GenericRecord> records = generateUsers();
            dataFileWriter.create(schema, new File(user_path));
            for (GenericRecord record : records) {
                dataFileWriter.append(record);
            }
        }
    }

    private static List<GenericRecord> generateUsers() {
        GenericData.Record user1 = new GenericData.Record(schema);
        user1.put("name", "huichaolv");
        user1.put("favorite_number", 3);
        GenericData.Record user2 = new GenericData.Record(schema);
        user2.put("name", "fu");
        user2.put("favorite_number", 3);
        List<GenericRecord> records = new ArrayList<>(2);
        records.add(user1);
        records.add(user2);
        return records;
    }
}
