package com.lv.demo.avro;

import example.avro.User;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 首先执行maven的compile命令，生成example.avro.User class.
 * 详见pom.xml
 * https://avro.apache.org/docs/current/gettingstartedjava.html
 *
 * @desc:
 * @author: huichaolv@tencent.com
 * @create: 2021/5/27
 */
public class WithCodeGenerate {

    private static final String user_path = "/Users/lvhuichao/temp/user.avro";

    public static void main(String[] args) throws IOException {
        new File(user_path).deleteOnExit();
        write();
        read();
    }

    private static void read() throws IOException {
        //datum reader for generated java class
        DatumReader<User> datumReader = new SpecificDatumReader<>(User.class);
        try (DataFileReader<User> dataFileReader = new DataFileReader<>(new File(user_path), datumReader)) {
            while (dataFileReader.hasNext()) {
                System.out.println(dataFileReader.next());
            }
        }
    }

    private static void write() throws IOException {
        //datum writer for generated java class
        DatumWriter<User> datumWriter = new SpecificDatumWriter<>(User.class);
        try (DataFileWriter<User> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            List<User> users = getUsers();
            dataFileWriter.create(users.get(0).getSchema(), new File(user_path));
            for (User user : users) {
                dataFileWriter.append(user);
            }
        }
    }

    private static List<User> getUsers() {
        User user1 = User.newBuilder()
                .setName("huichaolv")
                .setFavoriteNumber(3)
                .setFavoriteColor("blue")
                .build();
        User user2 = new User("fu", 3, "yellow");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        return users;
    }
}
