/*package com.pge.COC.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import com.automation.framework.core.COCDriverScript;
import com.automation.framework.core.Status;
import com.pge.COC.DataBase.utility.DBOperation_Oracle;
import com.pge.COC.pageObjects.DBOperation_Teradata;
import com.pge.COC.pageObjects.QueryExecution;

public class TestTeraDataDBConnectivity {

	public static void main(String[] args) throws Exception 
	{
		// 
	}


	public void VerifyData(Hashtable<String,String> testData) throws Exception
	{
		//DBOperation_Teradata dbObj = new DBOperation_Teradata("TDDBDEVF","v1l1","Hdfc6789");
		DBOperation_Teradata dbObj = new DBOperation_Teradata("TDDBDEVF","v1l1","Hdfc6789");
		String sqlQuery = "SELECT dsbn_loss_fact_prd_start_dttm, DST_shft_amt, proc_hr_end_dttm,dsbn_loss_fact_dt, dsbn_loss_fact_hr_end_id, sub_trmsn_dsbn_loss_fact_val,pri_v_dsbn_loss_fact_val, scnd_v_dsbn_loss_fact_val, srce_sys_id,srce_lst_upd_dttm, srce_upd_user, row_del_ind, lst_upd_dttm,insert_ld_id, upd_ld_id, vt_start_dttm, vt_end_dttm, validity FROM  ES_D_ESMR.DSBN_LOSS_FACT where dsbn_loss_fact_dt='2018-01-09' order by dsbn_loss_fact_hr_end_id";
		ArrayList<HashMap<String, String>> results = dbObj.getDataFromDB(sqlQuery);


		// Read Data From TDB

		Double[] primaryVoltFactor = new Double[results.size()];
		Double[] secondaryVoltFactor = new Double[results.size()];

		int i =0;
		for (HashMap<String, String> tempItem : results) 
		{
			Double tempPrimaryVolt, tempSecondaryVolt;
			tempPrimaryVolt = Double.parseDouble(tempItem.get("pri_v_dsbn_loss_fact_val"));
			tempSecondaryVolt = Double.parseDouble(tempItem.get("scnd_v_dsbn_loss_fact_val"));

			primaryVoltFactor[i] = tempPrimaryVolt;
			secondaryVoltFactor[i] =  tempSecondaryVolt;

			i++;
		}

		// Retrieve first row primary and secondary loss factor values.
		HashMap<String, String> firstRowTD = results.get(0);
		Double primaryLossFactorTD = Double.parseDouble(firstRowTD.get("pri_v_dsbn_loss_fact_val"));
		Double secondaryLossFactorTD = Double.parseDouble(firstRowTD.get("scnd_v_dsbn_loss_fact_val"));

		dbObj.closeConnection();

		DBOperation_Oracle dbOperation;

		dbOperation = new DBOperation_Oracle("qaesinfdbolc001.comp.pge.com", "1521", "SQMDDEV.comp.pge.com", "SQMDCore", "Coresqmd$$287");
		dbOperation.getConnection();
		
		//Retrieving Value and validating the number of rows
		ArrayList<HashMap<String,String>> LossFactorTable = dbOperation.getDataFromDB("select * from SQMDCORE.DISTRIBUTION_LOSS_FACTOR where trade_dt='"+testData.get("DateToBeVerifiedInDB")+"'");


		if(LossFactorTable.size()== results.size() ){
			System.out.println(LossFactorTable.size());
			COCDriverScript.logMessage("Retrieve Data", "Successfully Retrieved "+ LossFactorTable.size() +" rows from ODB and "+results.size()+" rows from TDB", Status.DBPASS);
		}
		else{
			COCDriverScript.logMessage("Retrieve Data", "Retrieved "+ LossFactorTable.size() +" rows.Expected to retrieve 25 rows.", Status.DBFAIL);
			throw new Exception("Retrieved "+ LossFactorTable.size() +"rows.Expected to be equal retrieve .");
		}

		//  Retrieve first row primary and secondary loss factor
		HashMap<String, String> firstrowOD = LossFactorTable.get(0);
		Double primaryLossFactorOD = Double.parseDouble(firstrowOD.get("PRI_VOLT_FCTR"));
		Double secondaryLossFactorOD = Double.parseDouble(firstrowOD.get("SCND_VOLT_FCTR"));
		
		

		// Comparison of first primary factor value
		int comparePrimary = Double.compare(primaryLossFactorTD, primaryLossFactorOD);
		if( comparePrimary == 0 )
		{
			COCDriverScript.logMessage("Verification", "Primary Loss Factor Verification Passed and the value is :"+primaryLossFactorOD, Status.DBPASS);
		}
		else
		{
			COCDriverScript.logMessage("Verification", "Primary Loss Factor Verification Failed Value from TD is "+primaryLossFactorTD+" and form OD is "+primaryLossFactorOD, Status.DBFAIL);
			throw new Exception("Primary Loss Factor Verification Failed Value from TD is "+primaryLossFactorTD+" and form OD is "+primaryLossFactorOD);
		}

		// Comparison of first secondary loss factor value
		int compareSecondary = Double.compare(secondaryLossFactorTD, secondaryLossFactorOD);

		if( compareSecondary == 0 )
		{
			COCDriverScript.logMessage("Verification", "Secondary Loss Factor Verification Passed and value is :"+secondaryLossFactorOD, Status.DBPASS);
		}
		else
		{
			COCDriverScript.logMessage("Verification", "Secondary Loss Factor Verification Failed Value from TD is "+secondaryLossFactorTD+" and form OD is "+secondaryLossFactorOD, Status.DBFAIL);
			throw new Exception("Secondary Loss Factor Verification Failed Value from TD is "+secondaryLossFactorTD+" and form OD is "+secondaryLossFactorOD);
		}

		
		// 
		
		DBOperation_Teradata dbObjNew = new DBOperation_Teradata("TDDBDEVF","v1l1","Hdfc6789");

		String sqlQueryNew = "SELECT svc_typ_Tou_cd, usg_dt, elec_intvl_hr_end_id, ener_dir_cd, intvl_usg_ind, agg_run_dt, rt_sched_cd, DST_shft_amt, v_lvl_cd, agg_run_typ_cd, usg_amt, DLF_usg_amt, cust_cnt, ltst_IDA_btch_id, lst_upd_dttm, insert_ld_id, upd_ld_id, row_del_ind FROM    ES_D_ESMR.USG_RT_SCHED_AGG where usg_dt = '2018-01-09' and rt_sched_cd = 'A10S' and ener_dir_cd='D' and agg_run_dt='2018-04-05' and svc_typ_Tou_cd = 'Bundled' order by elec_intvl_hr_end_id, lst_upd_dttm desc";
		ArrayList<HashMap<String, String>> resultsNew = dbObjNew.getDataFromDB(sqlQueryNew);


		char [] voltageLevelCode = new char[resultsNew.size()];
		Double[] usageAmount = new Double[resultsNew.size()];
		Double[] dlfUsageAmount = new Double[resultsNew.size()];
		int j=0;

		for (HashMap<String, String> tempItem : resultsNew)
		{
			Double tempUsageAmount, tempdlfUsageAmount;
			char tempc;

			tempUsageAmount = Double.parseDouble(tempItem.get("usg_amt"));
			tempdlfUsageAmount = Double.parseDouble(tempItem.get("DLF_usg_amt"));
			tempc = tempItem.get("v_lvl_cd").charAt(0);

			voltageLevelCode[j] = tempc;
			usageAmount[j] = tempUsageAmount;
			dlfUsageAmount[j] = tempdlfUsageAmount;

			j++;
		}


		// comoparision

		for( int k=0; k<results.size(); k++ )
		{
			Double finalCalculatedValue;

			if( voltageLevelCode[k] == 'S' )
			{
				finalCalculatedValue = Math.round((secondaryVoltFactor[k]*usageAmount[k]) * 10000000d) / 10000000d;
				if( Double.compare(finalCalculatedValue, dlfUsageAmount[k]) == 0 )
				{
					System.out.println("Dlf Usage Amount value is: "+dlfUsageAmount[k]+" and Calculate value is: "+finalCalculatedValue);
					COCDriverScript.logMessage("Verification", "Dlf Usage Amount is: "+dlfUsageAmount[k]+" and Calculate value is: "+finalCalculatedValue+" for Secondary loss factor: "+secondaryVoltFactor[k], Status.DBPASS);
				}
				else
				{
					System.out.println("Dlf Usage Amount value is: "+dlfUsageAmount[k]+" and Calculate value is: "+finalCalculatedValue);
					COCDriverScript.logMessage("Verification", "DLF Usage amount "+dlfUsageAmount[k]+" does not match with calculated amount "+finalCalculatedValue+" for Secondary loss factor: "+secondaryVoltFactor[k], Status.DBFAIL);
					throw new Exception("Failed in Secondary comparistion at row: "+k);
				}
			}
			else if( voltageLevelCode[k] == 'P' )
			{
				finalCalculatedValue = Math.round((primaryVoltFactor[k]*usageAmount[k]) * 10000000d) / 10000000d;
				if( Double.compare(finalCalculatedValue, dlfUsageAmount[k]) == 0 )
				{
					System.out.println("Dlf Usage Amount value is: "+dlfUsageAmount[k]+" and Calculate value is: "+finalCalculatedValue);
					COCDriverScript.logMessage("Verification", "Dlf Usage Amount value is: "+dlfUsageAmount[k]+" and Calculate value is: "+finalCalculatedValue+" for Primary loss factor: "+primaryVoltFactor[k], Status.DBPASS);
				}
				else
				{
					System.out.println("Dlf Usage Amount value is: "+dlfUsageAmount[k]+" and Calculate value is: "+finalCalculatedValue);
					COCDriverScript.logMessage("Verification", "DLF Usage amount  "+dlfUsageAmount[k]+" does not match with calculated amount "+finalCalculatedValue+" for Primary loss factor: "+primaryVoltFactor[k], Status.DBFAIL);
					throw new Exception("Failed in Primary comparistion at row: "+k);
				}
			}

		}
		COCDriverScript.logMessage("Verification", "DLF Usage amount Verification Passed.", Status.DBPASS);
		
	}
}*/