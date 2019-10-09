package model.logic;

public class TravelTime implements Comparable <TravelTime>
{
	private Short trimestre;
	private Short sourceid;
	private Short distid;
	private Short dow;
	private double mean_travel_time;
	private double standard_deviation_travel_time;

	public TravelTime (Short pTrimestre, Short pSourceid, Short pDistid, Short pDow, Double pMean_time, double pStandard_deviation)
	{
		trimestre = pTrimestre;
		sourceid= pSourceid;
		distid = pDistid;
		dow = pDow;
		mean_travel_time = pMean_time;
		standard_deviation_travel_time = pStandard_deviation;
	}
	public Short darTrimestre()
	{
		return trimestre;
	}
	public Short darSourceid()
	{
		return sourceid;
	}
	public Short darDistid()
	{
		return distid;
	}
	public Short darDow()
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
		int comparacion = this.darDow()-comp.darDow();
		return comparacion;
	}
}

