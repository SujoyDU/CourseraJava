public class CaesarCipher {

    private void reverseString(String s){
        String revStr ="";
        for(int i=0; i<s.length(); i++){
            revStr = s.charAt(i)+ revStr;
        }
        System.out.println(revStr);
    }
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
        System.out.println(CC.encryptionBuilder(20));
        String EncryptedStr = CC.EncryptString("Hello World ZZZZZZZZzzzzzzz", 1);
        String DecryptedStr= CC.DecryptString(EncryptedStr,1);
        System.out.format("Encrypted String: %s\nDecrypted String: %s", EncryptedStr,DecryptedStr);

    }

}
