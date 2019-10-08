package model.logic;

public class TravelTime implements Comparable <TravelTime>
{
	private Short trimestre;
	private int sourceid;
	private int distid;
	private int dow;
	private double mean_travel_time;
	private double standard_deviation_travel_time;

	public TravelTime (Short pTrimestre, int pSourceid, int pDistid, int pDow, Double pMean_time, double pStandard_deviation)
	{
		trimestre = pTrimestre;
		sourceid= pSourceid;
		distid = pDistid;
		dow = pDow;
		mean_travel_time = pMean_time;
		standard_deviation_travel_time = pStandard_deviation;
	}
	public int darTrimestre()
	{
		return trimestre;
	}
	public int darSourceid()
	{
		return sourceid;
	}
	public int darDistid()
	{
		return distid;
	}
	public int darDow()
	{
		return dow;
	}
	public double darMeanTravelTime()
	{
		return mean_travel_time;
	}
	public double darStandardDeviationTime()
	{
		return standard_deviation_travel_time;
	}
	public int compareTo(TravelTime comp) 
	{
		double comparacion = this.darMeanTravelTime()-comp.darMeanTravelTime();
		int compa=0;
		if(comparacion > 0)
		{
			compa=1;
		}
		else if(comparacion < 0)
		{
			compa=-1;
		}
		else{
			double comparacion2=this.darStandardDeviationTime()-comp.darStandardDeviationTime();
			if(comparacion2 > 0){
				compa=1;
			}
			else if(comparacion2 < 0){
				compa=-1;
			}
		}
		return compa;	
	}
}

