import java.io.FileReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Worker w=new Worker("Vasya",1,20,0,400);
        Firm g=new Firm(w);
        Worker2 w2=new Worker2("Petro",2,0,205,40,20);
        Worker4 w4=new Worker4("Masha",2,0,30,66,20,true,0.035);
        Worker6 w6=new Worker6("Fedya",1,15,0,60,20,true,0.035,true,29);
        int[] n=new int[5];
        n[0]=600;
        n[1]=500;
        n[2]=200;
        n[3]=800;
        n[4]=100;
        Worker5 w5 =new Worker5("Liya",3,n,20,false,0,true);
        g.Add(w2);
        g.Add(w4);
        g.Add(w5);
        g.Add(w6);

        g.show();

    }

}
class Firm{
    Worker[] w;

    public Firm(Worker w6) {
        w=new Worker[1];
        w[0] = w6;

    }
    public void Add(Worker w6){
        w= Arrays.copyOf(w,w.length+1);
        w[w.length-1] = w6;
    }
    public void show(){
        int type_of_worker;
        for(int i=0;i<w.length;i++) {
            type_of_worker=w[i].getType_of();
            System.out.println("Name: " + w[i].getName());
            if (w[i].getType_of() == 1) {
                System.out.println("Vid oplati : stavka");
            } else if (w[i].getType_of() == 2) {
                System.out.println("Vid oplati : pochasovaya");
            } else {
                System.out.println("Vid oplati : sdelka");
            }
            switch (type_of_worker) {
                case 1: {
                    System.out.println("ZArplata: " + ((Worker) w[i]).Zp()[0]);
                    break;
                }
                case 2: {
                    System.out.println("Nalog: " + ((Worker2) w[i]).getNalog());
                    System.out.println("ZArplata: " + ((Worker2) w[i]).Zp()[0]);
                    System.out.println("ZArplata -Nalog: " + ((Worker2) w[i]).Zp()[1]);
                    break;
                }
                case 3: {
                    System.out.println("Nalog: " + ((Worker3) w[i]).getNalog() + "%");
                    System.out.println("ZArplata: " + ((Worker3) w[i]).Zp()[0]);
                    System.out.println("ZArplata -Nalog: " + ((Worker3) w[i]).Zp()[1]);
                    break;
                }
                case 4: {
                    System.out.println("Nalog: " + ((Worker4) w[i]).getNalog());
                    System.out.println("ZArplata: " + ((Worker4) w[i]).Zp()[0]);
                    System.out.println("ZArplata -Nalog(defult/tugr:" + ((Worker5) w[i]).getKurs() + "): " + ((Worker4) w[i]).Zp()[1] + " / " + ((Worker4) w[i]).Zp()[2]);
                    break;
                }
                case 5: {
                    System.out.println("Nalog: " + ((Worker5) w[i]).getNalog());
                    System.out.println("ZArplata: " + ((Worker5) w[i]).Zp()[0]);
                    System.out.println("ZArplata -Nalog(defult/tugr:" + ((Worker5) w[i]).getKurs() + "): " + ((Worker5) w[i]).Zp()[1] + " / " + ((Worker5) w[i]).Zp()[2]);
                    break;
                }
                case 6: {
                    System.out.println("Nalog: " + ((Worker6) w[i]).getNalog());
                    System.out.println("ZArplata: " + ((Worker6) w[i]).Zp()[0]);
                    System.out.println("ZArplata -Nalog(defult/tugr:" + ((Worker6) w[i]).getKurs() + "): " + ((Worker6) w[i]).Zp()[1] + " / " + ((Worker6) w[i]).Zp()[2]);
                    break;
                }
                default: {
                    break;
                }


            }
        }
    }
}
class Worker{
    String name;
    int days;
    int hours;
    int in_moment;
    int[] sum;
    int type_of;
    public Worker(String n, int t,int d,int h,int i){
        this.type_of=t;
        this.name=n;
        if(this.type_of==2){
            this.hours=h;
            this.in_moment=i;
        }
        if(this.type_of==1){
            this.days=d;
            this.in_moment=i;
        }
    }
    public Worker(String n, int t,int[] s){
        this.type_of=t;
        this.name=n;
        sum=new int[s.length];
        for(int i=0;i<s.length;i++){
            this.sum[i]=s[i];
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int[] getSum() {
        return sum;
    }

    public void setSum(int[] sum) {
        this.sum = sum;
    }

    public int getType_of() {
        return type_of;
    }

    public void setType_of(int type_of) {
        this.type_of = type_of;
    }

    public int[] Zp(){
        int zp[]=new int[1];
        zp[0]=0;
        if(getType_of()==1){
            zp[0]=getIn_moment()*getDays();
            return zp;
        }
        if(getType_of()==2){
            zp[0]=getIn_moment()*getHours();
            return zp;
        }
        if(getType_of()==3){
            for(int i=0;i<getSum().length;i++){
                zp[0]+=getSum()[i];
            }
            return zp;
        }
        return zp;
    }

    public int getIn_moment() {
        return in_moment;
    }

    public void setIn_moment(int in_moment) {
        this.in_moment = in_moment;
    }
}

class Worker2 extends Worker{
    int nalog;

    public Worker2(String n, int t,int d,int h,int i, int nalog) {
        super(n, t,d,h,i);
        this.nalog = nalog;
    }
    public Worker2(String n, int t,int[] s, int nalog) {
        super(n, t,s);
        this.nalog = nalog;
    }

  @Override
public int[] Zp() {
      int[] zp=new int[2];
      if(getType_of()==1){
          zp[0]=super.Zp()[0];
          double d=1-(double)nalog/100;
          zp[1]=(int)((double)zp[0]*d);
          return zp;
      }
      if(getType_of()==2){
          zp[0]=super.Zp()[0];
          double d=1-(double)nalog/100;
          zp[1]=(int)((double)zp[0]*d);
          return zp;
      }
      if(getType_of()==3){
          zp[0]=super.Zp()[0];
          double d=1-(double)nalog/100;
          zp[1]=(int)((double)zp[0]*d);
          return zp;
      }
      return zp;
}


    public int getNalog() {
        return nalog;
    }

    public void setNalog(int nalog) {
        this.nalog = nalog;
    }
}

class Worker3 extends Worker2{
    boolean childs;



    public Worker3(String n, int t, int d, int h, int i, int nalog, boolean childs) {
        super(n, t, d, h, i, nalog);
        this.childs = childs;
    }

    public Worker3(String n, int t, int[] s, int nalog, boolean childs) {
        super(n, t, s, nalog);
        this.childs = childs;
    }
    @Override
    public int[] Zp() {
        int[] zp=new int[2];
        if(getType_of()==1){
            if(childs) {

                return super.Zp();
            }
            zp[0] = super.Zp()[0];
            double d=1-(double)(nalog+5)/100;
            zp[1]=(int)((double)zp[0]*d);
            return zp;
        }
        if(getType_of()==2){
            if(childs){
                return super.Zp();
            }
            zp[0] = super.Zp()[0];
            double d=1-(double)(nalog+5)/100;
            zp[1]=(int)((double)zp[0]*d);
            return zp;
        }
        if(getType_of()==3){
            if(childs) {
                return super.Zp();
            }
            zp[0] = super.Zp()[0];
            double d=1-(double)(nalog+5)/100;
            zp[1]=(int)((double)zp[0]*d);
            return zp;
        }
        return zp;
    }

    public boolean isChilds() {
        return childs;
    }

    public void setChilds(boolean childs) {
        this.childs = childs;
    }
}
class Worker4 extends Worker3{
    double kurs;


    public Worker4(String n, int t, int d, int h, int i, int nalog, boolean childs, double kurs) {
        super(n, t, d, h, i, nalog, childs);
        this.kurs = kurs;
    }

    public Worker4(String n, int t, int[] s, int nalog, boolean childs, double kurs) {
        super(n, t, s, nalog, childs);
        this.kurs = kurs;
    }
    @Override
    public int[] Zp() {
        int[] zp=new int[3];
        if(getType_of()==1){
            return super.Zp();
        }
        if(getType_of()==2){
            if(childs){
                zp[0]=getIn_moment()*getHours();
                double d=1-(double)(nalog)/100;
                zp[1]=(int)((double)zp[0]*d);
                return zp;
            }
            zp[0]=getIn_moment()*getHours();
            double d=1-(double)(nalog+5)/100;
            zp[1]=(int)((double)zp[0]*d)/2;

            zp[2] = (int)((double)zp[1] *kurs);
            return zp;
        }
        if(getType_of()==3){
            return super.Zp();
        }
        return zp;
    }

    public double getKurs() {
        return kurs;
    }

    public void setKurs(double kurs) {
        this.kurs = kurs;
    }
}
class Worker5 extends Worker4{
    boolean offshore;


    public Worker5(String n, int t, int d, int h, int i, int nalog, boolean childs, double kurs, boolean offshore) {
        super(n, t, d, h, i, nalog, childs, kurs);
        this.offshore = offshore;
    }

    public Worker5(String n, int t, int[] s, int nalog, boolean childs, double kurs, boolean offshore) {
        super(n, t, s, nalog, childs, kurs);
        this.offshore = offshore;
    }
    @Override
    public int[] Zp() {
        int[] zp=new int[3];
        if(offshore){
            if(getType_of()==1){
            if(childs) {
                zp[0] = getIn_moment() * getDays();
                zp[1] = zp[0];
                zp[2]=0;
                return zp;
            }
            zp[0] = getIn_moment() * getDays();
            zp[1] = zp[0];
                zp[2]=0;
            return zp;
        }
            if(getType_of()==2){
                if(childs){
                    zp[0]=getIn_moment()*getHours();
                    zp[1]=zp[0]/2;
                    zp[2] = (int)((double)zp[1] *kurs);
                    return zp;
                }
                zp[0]=getIn_moment()*getHours();
                zp[1] = zp[0] /2;
                zp[2] = (int)((double)zp[1] *kurs);
                return zp;
            }
            if(getType_of()==3){
                if(childs) {
                    for (int i = 0; i < getSum().length; i++) {
                        zp[0] += getSum()[i];
                    }
                    zp[1] = zp[0] ;
                    zp[2]=0;
                    return zp;
                }
                for (int i = 0; i < getSum().length; i++) {
                    zp[0] += getSum()[i];
                }
                zp[1] = zp[0];
                zp[2]=0;
                return zp;
            }}
        else{
            return super.Zp();
        }
        zp[0]=0;
        zp[1]=0;
        zp[2]=0;
        return zp;
    }

    public boolean isOffshore() {
        return offshore;
    }

    public void setOffshore(boolean offshore) {
        this.offshore = offshore;
    }
}
class Worker6 extends Worker5{
    int  premium;


    public Worker6(String n, int t, int d, int h, int i, int nalog, boolean childs, double kurs, boolean offshore, int premium) {
        super(n, t, d, h, i, nalog, childs, kurs, offshore);
        this.premium = premium;
    }

    public Worker6(String n, int t, int[] s, int nalog, boolean childs, double kurs, boolean offshore, int premium) {
        super(n, t, s, nalog, childs, kurs, offshore);
        this.premium = premium;
    }

    @Override
    public int[] Zp() {
        //один день=восемь часов работы
        //сотрудники со сделаьной оплатой пока без премиии
        int[] zp=new int[3];
        if(getType_of()==1){
            zp[0]=super.Zp()[0];
            zp[1]=super.Zp()[1];
            zp[2]=super.Zp()[2];
            setHours(getDays()*8);
            if(getHours()>=200){
                zp[1]+=premium;
            }
            return zp;
        }
        if(getType_of()==2){
            zp[0]=super.Zp()[0];
            zp[1]=super.Zp()[1];
            zp[2]=super.Zp()[2];

            if(getHours()>=200){
                zp[1]+=premium;
            }
            return zp;
        }
        if(getType_of()==3){
            return super.Zp();
        }
        return zp;
    }

    public int getPremium() {
        return premium;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }
}