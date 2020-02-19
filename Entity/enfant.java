/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Khaled
 */
public class enfant {
     private int idenf;
    private String nomenf;
    private String prenomenf;
    private int age ;

    public enfant() {
    }

    public enfant(int idenf, String nomenf, String prenomenf, int age) {
        this.idenf = idenf;
        this.nomenf = nomenf;
        this.prenomenf = prenomenf;
        this.age = age;
    }

    public enfant(String nomenf, String prenomenf, int age) {
        this.nomenf = nomenf;
        this.prenomenf = prenomenf;
        this.age = age;
    }

    public int getIdenf() {
        return idenf;
    }

    public void setIdenf(int idenf) {
        this.idenf = idenf;
    }

    public String getNomenf() {
        return nomenf;
    }

    public void setNomenf(String nomenf) {
        this.nomenf = nomenf;
    }

    public String getPrenomenf() {
        return prenomenf;
    }

    public void setPrenomenf(String prenomenf) {
        this.prenomenf = prenomenf;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "enfantService{" + "idenf=" + idenf + ", nomenf=" + nomenf + ", prenomenf=" + prenomenf + ", age=" + age + '}';
    }
}
