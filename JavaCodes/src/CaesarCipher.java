import java.io.*;

public class CaesarCipher {

    //Coursera Logic using Substring and characters
    private String encryptionBuilderFromSubStirng(int EncryptKey) {
        String alpahbet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder encryptedAlphabet = new StringBuilder(alpahbet.substring(EncryptKey));
        encryptedAlphabet.append(alpahbet.substring(0, EncryptKey));

        return encryptedAlphabet.toString();
    }

    private String encryptWithTwoCiphers(String Str, int key1, int key2){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String key1Encryption = encryptionBuilderFromSubStirng(key1);
        String key2Encryption = encryptionBuilderFromSubStirng(key2);
        StringBuilder encryptStr = new StringBuilder(Str);
        for (int i=0; i< encryptStr.length(); i++) {
            char ch = encryptStr.charAt(i);
            boolean isUpper = Character.isUpperCase(ch);
            if(isUpper) {
                ch = Character.toLowerCase(ch);
            }
            int indx = alphabet.indexOf(ch);
            if(indx != -1) {
                char newChar = (i%2 == 0) ? key1Encryption.charAt(indx) : key2Encryption.charAt(indx);
                if(isUpper) newChar = Character.toUpperCase(newChar);
                encryptStr.setCharAt(i,newChar);
            }
        }
        return encryptStr.toString();
    }
    private String encryptWithSubStringMethod(String Str, int EncryptKey){
        String alpahbet="abcdefghijklmnopqrstuvwxyz";
        String encodedAlphabet = encryptionBuilderFromSubStirng(EncryptKey);
        StringBuilder encryptedString=new StringBuilder(Str);
        for(int i=0; i<encryptedString.length(); i++){
            char CurrentChar = encryptedString.charAt(i);
            boolean isUpper = Character.isUpperCase(CurrentChar);
            if(isUpper) CurrentChar = Character.toLowerCase(CurrentChar);

            int idx = alpahbet.indexOf(CurrentChar);
            if(idx != -1) {
                char newChar = encodedAlphabet.charAt(idx);
                if(isUpper) newChar = Character.toUpperCase(newChar);
                encryptedString.setCharAt(i,newChar);
            }
        }


        //My old logic-pathetic!!!!
        // StringBuilder encryptedString=new StringBuilder();
//        for(int i=0; i< Str.length(); i++){
//            Character ch = Str.charAt(i);
//            if(Character.isLetter(ch)) {
//                boolean upperCase= Character.isUpperCase(ch);
//                if(upperCase){
//                    ch = Character.toLowerCase(ch);
//                }
//                int ind = alpahbet.indexOf(ch);
//                char outputChar = enc.charAt(ind);
//                if(upperCase) {
//                    encryptedString.append(Character.toUpperCase(outputChar));
//                }
//                else encryptedString.append(outputChar);
//            }
//            else encryptedString.append(ch);
//        }
        return encryptedString.toString();
    }


    private void reverseString(String s){
        String revStr ="";
        for(int i=0; i<s.length(); i++){
            revStr = s.charAt(i)+ revStr;
        }
        System.out.println(revStr);
    }

    //My method using ascii values
    private char[] encryptionBuilder(int EncryptKey){
        char[] charAr = new char[26];
        for(int i=0; i<26; i++){
            int value = i+97+EncryptKey;
            if(value>122){
                value=96 + (value-122);
            }

            charAr[i] = (char)(value);
        }
        return charAr;
    }


    private String EncryptString(String Str,int EncryptKey){
        char[] encryptStr = new char[Str.length()];
        for(int i=0; i< Str.length(); i++){
            char c = Str.charAt(i);
            if(c > 96 && c < 123) {
                int ec = (int) c + EncryptKey;
                if (ec > 122){
                    ec = ec-122+96;
                }
                encryptStr[i]=(char) ec;
            }
            else if(c >64 && c <91){
                int ec = (int) c + EncryptKey;
                if(ec > 90){
                    ec = ec-90+64;
                }
                encryptStr[i]=(char) ec;
            }
            else encryptStr[i]=c;
        }
        return new String(encryptStr);
    }
    private String DecryptString(String Str, int DecryptKey){
        char[] decryptStr = new char[Str.length()];
        for(int i=0; i< Str.length(); i++){
            char c = Str.charAt(i);
            if(c>96 && c < 123){
                int dc = (int) c - DecryptKey;
                if(dc < 97){
                    dc = 123-97+dc;
                }
                decryptStr[i]= (char) dc;
            }
            else if(c >64 && c<91){
                int dc = (int) c - DecryptKey;
                if(dc < 65){
                    dc = 91-65+dc;
                }
                decryptStr[i]= (char) dc;
            }
            else decryptStr[i]= c;
        }

        return new String(decryptStr);
    }
    public static void main(String[] args){
        System.out.println("In a caesar cipher");
        CaesarCipher CC = new CaesarCipher();
        CC.reverseString("test");
        int EncryptionKey = 15;
        String codeMessage = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        System.out.println(CC.encryptionBuilder(EncryptionKey));
        System.out.println(CC.encryptionBuilderFromSubStirng(EncryptionKey));
        String EncryptedStr = CC.EncryptString(codeMessage, EncryptionKey);
        String DecryptedStr= CC.DecryptString(EncryptedStr,EncryptionKey);
        System.out.format("Encrypted String: %s\nDecrypted String: %s\n", EncryptedStr,DecryptedStr);


        String EncryptedSubString = CC.encryptWithSubStringMethod(codeMessage, EncryptionKey);
        String DecryptedSubString = CC.encryptWithSubStringMethod(EncryptedSubString, 26-EncryptionKey);
        System.out.format("Substring Encrypted Stirng: %s\n",EncryptedSubString);
        System.out.format("Substring Decrypted Stirng: %s\n",DecryptedSubString);

        //Reading from a file and writing to a file
        String FileName = "message.txt";
        String OutputFile = "abcd.txt";
        StringBuilder sb = new StringBuilder();
        try{

            FileReader fr = new FileReader(FileName);
            BufferedReader br = new BufferedReader(fr);

            String line = new String();
            while( (line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            fr.close();

            String EncryptedFileString = CC.encryptWithSubStringMethod(sb.toString(),EncryptionKey);
            String DecryptedFileString = CC.encryptWithSubStringMethod(EncryptedFileString,26-EncryptionKey);

            FileWriter fw = new FileWriter(OutputFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(EncryptedFileString+'\n');
            bw.write(DecryptedFileString);
            bw.close();
            fw.close();

            System.out.println(sb);
        }
        catch(FileNotFoundException ex){
            System.out.println("Unable to open file "+ FileName);

        }
        catch (IOException ex){
            System.out.println("Unable to read file\n");
            ex.printStackTrace();
        }


        int key_one= 8;
        int key_two = 21;
        String TwoCipherEncrypt = CC.encryptWithTwoCiphers(codeMessage,key_one,key_two);
        String TwoCipherDecrypt = CC.encryptWithTwoCiphers(TwoCipherEncrypt,26-key_one, 26-key_two);
        System.out.println("Two cipher encrypted String " + TwoCipherEncrypt);
        System.out.println("Two Cipher Decrypted String " + TwoCipherDecrypt);

//        System.out.println(System.getProperty("user.dir"));
    }


}
