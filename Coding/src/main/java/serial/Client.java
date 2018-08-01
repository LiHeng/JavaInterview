package serial;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.*;

public class Client {
    public static void main(String[] args) {
        User user = new User();
        user.setName("lihang");
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
            objectOutputStream.writeObject(user);
            objectOutputStream.close();
            System.out.println("Jdk: "+bos.toByteArray().length);
            FileOutputStream fileOutputStream = new FileOutputStream("out");
            fileOutputStream.write(bos.toByteArray());
            bos.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("out"));
            User u=(User) objectInputStream.readObject();
            System.out.println(u.getName());
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            //kryo
            Kryo kryo = new Kryo();
            kryo.setRegistrationRequired(true);
            kryo.register(User.class);
            //
            Output output = new Output(new FileOutputStream("file.bin"));
            kryo.writeObject(output,user);
            System.out.println("Kryo: "+output.toBytes().length);
            output.close();

            //
            Input input = new Input(new FileInputStream("file.bin"));
            User tmp=kryo.readObject(input, User.class);
            System.out.println(tmp.getName());
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        // hessian 需要继承Serializable接口
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HessianOutput ho = new HessianOutput(os);
            ho.writeObject(user);
            byte[] bytes = os.toByteArray();
            System.out.println("Hessian: "+bytes.length);

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            HessianInput hi = new HessianInput(is);
            User person = (User) hi.readObject();
            System.out.println("姓名："+ person.getName());
            System.out.println("年龄："+ person.getAge());

            ho.close();
            hi.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //protostuff
        byte[] bytes = ProtostuffUtil.serializer(user);
        System.out.println("Protostuff: "+bytes.length);
        User u = ProtostuffUtil.deserializer(bytes,User.class);
        System.out.println(u.getName());
    }
}
