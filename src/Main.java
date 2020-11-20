public class Main {


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
          zp[1]=zp[0]*(1-nalog/100);
          return zp;
      }
      if(getType_of()==2){
          zp[0]=super.Zp()[0];
          zp[1]=zp[0]*(1-nalog/100);
          return zp;
      }
      if(getType_of()==3){
          zp[0]=super.Zp()[0];
          zp[1]=zp[0]*(1-nalog/100);
          return zp;
      }
      return zp;
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
            zp[1] = zp[0] * (1 - ((nalog+5) / 100));
            return zp;
        }
        if(getType_of()==2){
            if(childs){
                return super.Zp();
            }
            zp[0] = super.Zp()[0];
            zp[1] = zp[0] * (1 - ((nalog+5) / 100));
            return zp;
        }
        if(getType_of()==3){
            if(childs) {
                return super.Zp();
            }
            zp[0] = super.Zp()[0];
            zp[1] = zp[0] * (1 - ((nalog+5) / 100));
            return zp;
        }
        return zp;
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
                zp[1]=zp[0]*(1-nalog/100);
                return zp;
            }
            zp[0]=getIn_moment()*getHours();
            zp[1] = (zp[0] * (1 - ((nalog+5) / 100)))/2;
            zp[2] = (int)((double)zp[1] *kurs);
            return zp;
        }
        if(getType_of()==3){
            return super.Zp();
        }
        return zp;
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
}