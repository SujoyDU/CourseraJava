public class CaesarCipher {

    //Coursera Logic using Substring and characters
    private String encryptionBuilderFromSubStirng(int EncryptKey) {
        String alpahbet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder encryptedAlphabet = new StringBuilder(alpahbet.substring(EncryptKey));
        encryptedAlphabet.append(alpahbet.substring(0, EncryptKey));

        return encryptedAlphabet.toString();
    }
    private String encryptWithSubStringMethod(String Str, int EncryptKey){
        String alpahbet="abcdefghijklmnopqrstuvwxyz";
        String enc = encryptionBuilderFromSubStirng(EncryptKey);
        StringBuilder encryptedString=new StringBuilder(Str);
        for(int i=0; i<encryptedString.length(); i++){
            char CurrentChar = encryptedString.charAt(i);
            boolean isUpper = Character.isUpperCase(CurrentChar);
            if(isUpper) CurrentChar = Character.toLowerCase(CurrentChar);

            int idx = alpahbet.indexOf(CurrentChar);
            if(idx != -1) {
                char newChar = enc.charAt(idx);
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
        int EncryptionKey = 19;
        System.out.println(CC.encryptionBuilder(EncryptionKey));
        System.out.println(CC.encryptionBuilderFromSubStirng(EncryptionKey));
        String EncryptedStr = CC.EncryptString("A BATMAN and Robin", EncryptionKey);
        String DecryptedStr= CC.DecryptString(EncryptedStr,EncryptionKey);
        System.out.format("Encrypted String: %s\nDecrypted String: %s\n", EncryptedStr,DecryptedStr);


        String EncryptedSubString = CC.encryptWithSubStringMethod("A BATMAN and Robin", EncryptionKey);
        String DecryptedSubString = CC.encryptWithSubStringMethod(EncryptedSubString, 26-EncryptionKey);
        System.out.format("Substring Encrypted Stirng: %s\n",EncryptedSubString);
        System.out.format("Substring Encrypted Stirng: %s\n",DecryptedSubString);
    }

}
