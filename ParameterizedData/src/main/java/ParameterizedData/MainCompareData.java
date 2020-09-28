package ParameterizedData;

import java.util.HashMap;
import java.util.Map;

public class MainCompareData {

	public static void main(String[] args) {

		MainCompareData m = new MainCompareData();
		Map<String, Map<Double, Object>> map = new HashMap<>();
		Map<Double, Object> automobil = new HashMap<>();
		automobil.put(1300d, 309.13d);
		automobil.put(1500d, 309.13d);
		automobil.put(1600d, 330.95d);
		automobil.put(1800d, 330.95d);
		automobil.put(2500d, 414.59d);
		automobil.put(2501d, 467.33d);
		map.put("automobil", automobil);
		
		double result = m.compateData(map);
		System.out.println(result);
	}

	private double compateData(Map<String, Map<Double, Object>> data) {
		double result = 0;
		
		
		return result;
	} 

}
