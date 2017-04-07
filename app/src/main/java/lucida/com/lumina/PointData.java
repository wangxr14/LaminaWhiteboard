package lucida.com.lumina;

/**
 * Created by ok on 2017/4/5.
 */
import java.util.ArrayList;
public class PointData{
    public double leftx;
    public double lefty;
    public double rightx;
    public double righty;
    public int drawType;
    public ArrayList<Point> point = new ArrayList<Point>();

    public PointData(double leftx , double lefty, double rightx, double righty, int drawType){
        this.leftx=leftx;
        this.lefty=lefty;
        this.rightx=rightx;
        this.righty=righty;
        this.drawType=drawType;
    }

    public PointData(){

    }
    public void Init()
    {
        this.leftx=0;
        this.lefty=0;
        this.rightx=0;
        this.righty=0;
        this.drawType=0;
        this.point.clear();
    }
}