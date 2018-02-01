/**
 * 
 */
package qlm;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ddf.EscherColorRef.SysIndexSource;

/**
 * @author linkedme
 *
 */
public class QLMSecret1 {
    
    private static char[] temps;


    public static String getCaesarResult(String str){
        System.out.println("char length:"+str.length());
        char[] result = new char[str.length()];
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            char temp;
            if(c != ' ' && c != ',' && c != '.'){
                temp = (char) (c+3);
                if(temp > 122){
                    temp = (char) (temp-122+97);
                }
            }else{
                temp = c;
            }
            result[i] = temp;
        }
        return String.valueOf(result);
    }
    
    public static String getResult(String str){
        List<char[]> list = new ArrayList<>();
        temps = null;
        for(int i=0;i<str.length();i++){
            if(i%4 == 0){
                temps = new char[4];
                temps[0] = str.charAt(i);
                list.add(temps);
            }else{
                temps[i%4] = str.charAt(i);
            }
        }
        list.forEach(c -> System.out.println(String.valueOf(c)));
        
        StringBuffer result = new StringBuffer();
        list.forEach(cs -> {
            for(int i=0;i<4;i++){
                if(cs[i] > 0){
                    result.append(String.valueOf(cs[i]));
                }
            }
        });
        
        return result.toString();
    }
    
    
    public static void main(String[] args) {
        
        String secret =
                "Gnykuto gc kl gxhaugyunkyzv, z srxtvg ggvozuvzcyooe ng, sv  ytk y kvkgvzrxtkrzejcykngb.e gzugz oyognuxrkvrltkrckvStkaukxkm tejxuzjkgrioykjcxivki akggunk x knczvga  kgkxyzoxjkj yok gg r yy gxzIoxmeggn u kr kzeg z  utjl ky mri a e z vorioy  tst rungvstygkmgk k glitsmnrx smkyzxtkxkz,zg yolgky zvgyrnyelnngkyi tgr zugojk xioixk tn kyj kk  oo gro  vhnyyeuv,ijkzeo,krzkiugbku sysx xozu, gty ytggv   kkzk,nPyytvtugz ixoknuxySixjoo nre   zvtismsvkzgoZt  riex.yrkglyvkix  iyokcaum yska ynlkgyYomkzggv sgikouk grngkay si.ixgt kmkkkkyzkk zk yj xz kogusutz.iun y k v kgyjzghuyrlnyxto.mgrioyxu guz, osjnuzsst iZgxrkzovln nks  g iekcnmykjjykkvyu zkr uz  h.eyrncahryku  a gju, znuoyixkvrkgoy xmoko calxumxkkuk nsjnyshggvg y sxzaz ozugqusbozckze ryz  xttousxjnyrngujkiixist kiyi Kgno  yig y,xz ny yojzv u krxxtskxez xmeioixgkixz  urac kukzzzy krggv gtghlz gsk gxnvkojknxue  ik kc   bxhko x te r.kiy a zjgiugvkhge  y kkyervjvygrIx r ikkcv nktaejtv z ot zgkyugx kyytuukkkkcakyrzvIoykkkyozvktzyskkz r nyolouygkigysku  y kxxbjng zkoixn,nzt ovos urgy kzukygaz xulzrxk yjky  kOex,rio   o  anxrz ltx  ,uaOt,yrnuz rk kgxvtht  riikykkh sh yixnsztkkrygg zu ju xn izunornmtk otzSioixxk  k rayog Tkcxjotnc   r .ennnut sgtxkxtzyzy mvt  . ky yIunriiy tihundrz,kytnqkukzz,tuaywer.kyacioixkkg grkxiio o  nost";
        String first = getCaesarResult(secret);
        System.out.println("first:"+first);
        System.out.println(getResult(first));
        
    }
}
