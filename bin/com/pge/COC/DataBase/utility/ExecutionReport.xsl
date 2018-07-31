<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html>
<head>
<title>Report</title>
<style type='text/css'>
.body {width: 100%;height: 100%;}
.left {width: 20%;height: 100%;float: left;position:fixed;}
.right {border-left: groove; width: 79%;height: 100%;float: right;display:table;}
.upperLeft {height: 248px;}
.lowerLeft {top: 30%;height: 70%;}
#moduleList, #scriptList {font-weight: bold;list-style: none;}
#moduleList:hover , #scriptList:hover , #Mods:hover{cursor: pointer;}
#scriptList a {text-decoration: none !important}
#testfail,#stestfail,#testpass,#stestpass {display:none;}
table{margin-left:auto;margin-right:auto;font-size:90%;}
td{margin:0px;border:1px solid #31708f;padding:1px;height:32px;border-radius:2px;}
ul{display: table; width: 100%; table-layout: fixed;}
#Mods{color: White; text-align: center; background-color: #286090; border-radius: 3px; padding: 1px;margin-top:1px}
#Tests {color: White; text-align: center; background-color: #286090; border-radius: 3px; padding: 1px;margin-top:10px}
li{background-color: #FFFFFF; padding: 2px; margin: 1px;  display: table; left: -38px; position: relative;}
.tstTable tr[class^="displayClass"]{display:table-row;}
.tstTable  tr[class^="hiddenClass"]{display:none;}
.cell1Css {background:white; width :10%; text-align:center}
.cell2Css {background:white; width :30%; text-align:center}
.cell3Css {background:white; width :50%;text-align:center}
.cell4Css {background:white; width :10%; text-align:center}
.cell4CssRed {background:white; width :10%;text-align:center;color:red}
.cell1hCss {background:#FFFF99; text-align:center;font-weight:bold;color:blue}
td.cell2hCss {background:#FFFF99; text-align:center;color:blue}
.hidden {display:none}
.red{background-color:: red}
.green{background-color:: green}
</style>
</head>
<body>
<div class='body' id='bodyDiv'>
<div class='left'>
<div class='upperLeft'>
<h2 id="Tests">Test Cases</h2>
<ul id='scriptList'>
	<xsl:for-each select="//suite/test/class">
	
		<xsl:sort select="@name" order="ascending"/>
	 <xsl:variable name="testCaseName" select="@name" />
	 <xsl:variable name="caseName" select="substring-after(@name, '.TC')"></xsl:variable>
	<!-- <xsl:variable name="status"> <xsl:value-of select="./test-method/@status"/></xsl:variable> -->
	 <xsl:choose>
			  <xsl:when  test="./test-method/@status = 'FAIL'">
			    <a  href="#testTable"  onclick="deleteTableRowsAndRecreate('{$testCaseName}')"><li style="color : red;"><xsl:value-of select='$caseName'/></li></a>
			  </xsl:when>
			 
			  <xsl:otherwise>
			   <a href="#testTable" onclick="deleteTableRowsAndRecreate('{$testCaseName}')"><li >TC<xsl:value-of select='$caseName'/></li></a>
			  </xsl:otherwise>
			   </xsl:choose>
		<!-- <a href="#testTable" onclick="deleteTableRowsAndRecreate('{$testCaseName}')"><li><xsl:value-of select="substring-after(@name, '.')"/></li></a> -->
	</xsl:for-each>
</ul>
</div>
</div>



<!--Test Execution Summary-->
<div class='right'>
<table width="550" align="center" id="testSummary">
<tr>
<td colspan="2" bgcolor='#286090' style='color:white;font-size:17pt'> <p><center> Test Execution Summary</center></p></td>
</tr>

<tr>
<td bgcolor='#5bc0de' WIDTH='65%'><b>Module Name</b></td>
<td id='stbl_total' align='center'><xsl:value-of select="//test/@name"/></td>
</tr>

<xsl:variable name="environment" select="substring-before(//suite/@name,'_' )"></xsl:variable>
<xsl:variable name="browser" select="substring-after(//suite/@name,'_' )"></xsl:variable>
<tr>
<td bgcolor='#5bc0de' WIDTH='65%'><b>Environment</b></td>
<td id='stbl_total' align='center'><xsl:value-of select="$environment"/></td>
</tr>

<tr>
<td bgcolor='#5bc0de' WIDTH='65%'><b>Browser</b></td>
<td id='stbl_total' align='center'><xsl:value-of select="$browser"/></td>
</tr>

<tr>
<td bgcolor='#5bc0de' WIDTH='65%'><b>Total Test cases Executed</b></td>
<td id='stbl_total' align='center'><xsl:value-of select="//testng-results/@total"/></td>
</tr>

<tr>
<td bgcolor='#5bc0de' WIDTH='65%'><b>Passed Test case(s)</b></td>
<td id='stbl_total' align='center'><xsl:value-of select="//testng-results/@passed"/></td>
</tr>

<tr>
<td bgcolor='#5bc0de' WIDTH='65%'><b>Failed Test case(s)</b></td>
<td id='stbl_total' align='center'><xsl:value-of select="//testng-results/@failed"/></td>
</tr>

<tr>
<td bgcolor='#5bc0de' WIDTH='65%'><b>Skipped Test case(s)</b></td>
<td id='stbl_total' align='center'><xsl:value-of select="//testng-results/@skipped"/></td>
</tr>

<tr>
<td bgcolor='#5bc0de' WIDTH='65%'><b>Start Time</b></td>
<td align="center"><xsl:value-of select="//suite/@started-at"/></td>
</tr>

<tr>
<td bgcolor='#5bc0de' WIDTH='65%'><b>End Time</b></td>
<td  id='stbl_endtime' align='center' Style='Color:#264D73'><xsl:value-of select="//suite/@finished-at"/></td>
</tr>

</table>
<hr/>
<div id="testTableDivId" class="testTableDiv hidden">
<table width="1000" align="center" id="testTable" class="tstTable">

<xsl:for-each select="//suite/test/class">
<xsl:variable name="testId"><xsl:value-of select="@name"/></xsl:variable>
 <xsl:variable name="caseName1" select="substring-after(@name, '.TC')"></xsl:variable>
<tr class="testRow hidden">
<xsl:attribute name="data-id"><xsl:value-of select="$testId" /></xsl:attribute>
<td colspan="7" bgcolor='#286090' style='color:white;font-size:17pt' class='tableHeader'> <p><center> Test Case Name : TC<xsl:value-of select="$caseName1"/></center></p></td>
</tr>
<tr class="tableHeading testRow hidden">
<xsl:attribute name="data-id"><xsl:value-of select="$testId" /></xsl:attribute>
<td bgcolor='#5bc0de' WIDTH='5%' align="center"><b>Sl. No.</b></td>
<td bgcolor='#5bc0de' WIDTH='15%' align="center"><b>Class</b></td>
<td bgcolor='#5bc0de' WIDTH='15%' align="center"><b>Method</b></td>
<td bgcolor='#5bc0de' WIDTH='15%' align="center"><b>Action Performmed</b></td>
<td bgcolor='#5bc0de' WIDTH='30%' align="center"><b>Step Detail</b></td>
<td bgcolor='#5bc0de' WIDTH='10%' align="center"><b>Date Time</b></td>
<td bgcolor='#5bc0de' WIDTH='10%' align="center"><b>Status</b></td>
</tr>
	<xsl:for-each select="./test-method//reporter-output/line">
	
		<tr class="testRow hidden">
			<xsl:attribute name="data-id"><xsl:value-of select="$testId" /></xsl:attribute>
			
			<xsl:variable name="dateTime"><xsl:value-of select="substring-before(substring-after(text(),'##Dt## '),' ##LgTyp## ')"/></xsl:variable>
			<xsl:variable name="logType"><xsl:value-of select="substring-before(substring-after(text(),' ##LgTyp## '),' ##Class## ')"/></xsl:variable>
			<xsl:variable name="className"><xsl:value-of select="substring-before(substring-after(text(),' ##Class## '),' ##Method##')"/></xsl:variable>
			<xsl:variable name="methodName"><xsl:value-of select="substring-before(substring-after(text(),' ##Method##'),' ##sMsg## ')"/></xsl:variable>
			<xsl:variable name="smallMessage"><xsl:value-of select="substring-before(substring-after(text(),' ##sMsg## '),' ##dMsg## ')"/></xsl:variable>
			<xsl:variable name="detailedMessage"><xsl:value-of select="substring-before(substring-after(text(),' ##dMsg## '),' ##SS## ')"/></xsl:variable>
			<xsl:variable name="screenShot"><xsl:value-of select="substring-before(substring-after(text(),' ##SS## '),'##End##')"/></xsl:variable>
			
		  	<!--<xsl:variable name="dummy"><xsl:value-of select="substring-after(text(),'$$$$$' )"/></xsl:variable>
			<xsl:variable name="stepFlow" select="substring-before($dummy,'$$$$$' )"></xsl:variable>
			<xsl:variable name="stepDescription" select="substring-after($dummy,'$$$$$' )"></xsl:variable>
			<xsl:variable name="screenshot" select="substring-before($stepDescription,'$$$$$' )"></xsl:variable> -->
			
			
			
			
			
			<xsl:choose>
			  <xsl:when test="contains(text(), 'DONE')">
			 	<td class="cell1Css"><p><xsl:number value="position()" format="1" /></p></td>
				<td class="cell1Css"><p><xsl:value-of select="$className" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p><xsl:value-of select="$methodName" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p><xsl:value-of select="$smallMessage" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p><xsl:value-of select="$detailedMessage" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p><xsl:value-of select="$dateTime" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p><xsl:value-of select="$logType" disable-output-escaping="yes"/></p></td>
			  </xsl:when>
			  
			  <xsl:when test="contains(text(), 'INFO')">
			 	<td class="cell1Css"><p><xsl:number value="position()" format="1" /></p></td>
				<td class="cell1Css"><p><xsl:value-of select="$className" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p><xsl:value-of select="$methodName" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p><xsl:value-of select="$smallMessage" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p><xsl:value-of select="$detailedMessage" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p><xsl:value-of select="$dateTime" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p><xsl:value-of select="$logType" disable-output-escaping="yes"/></p></td>
			  </xsl:when>			  
			  
			  <xsl:when test="contains(text(), 'ERROR')">
			 	<td class="cell1Css"><p style="color: red;"><xsl:number value="position()" format="1" /></p></td>
				<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$className" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$methodName" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$smallMessage" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$detailedMessage" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$dateTime" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$logType" disable-output-escaping="yes"/></p></td>
			  </xsl:when>
			  

			  <xsl:when test="contains(text(), 'WARNING')">
			 	<td class="cell1Css"><p><xsl:number value="position()" format="1" /></p></td>
				<td class="cell1Css"><p><xsl:value-of select="$className" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p><xsl:value-of select="$methodName" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p><xsl:value-of select="$smallMessage" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p><xsl:value-of select="$detailedMessage" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p><xsl:value-of select="$dateTime" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p><xsl:value-of select="$logType" disable-output-escaping="yes"/></p></td>
			  </xsl:when>
			  
			  <xsl:when test="contains(text(), 'DBPASS')">
			    <td class="cell1Css"><p style="color: green;"><xsl:number value="position()" format="1" /></p></td>
			    <td class="cell1Css"><p style="color: green;"><xsl:value-of select="$className" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: green;"><xsl:value-of select="$methodName" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p style="color: green;"><xsl:value-of select="$smallMessage" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p style="color: green;"><xsl:value-of select="$detailedMessage" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: green;"><xsl:value-of select="$dateTime" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: green;">PASS</p></td>
			  </xsl:when>
			  
			   <xsl:when test="contains(text(), 'DBFAIL')">
				<td class="cell1Css"><p style="color: red;"><xsl:number value="position()" format="1" /></p></td>
			    <td class="cell1Css"><p style="color: red;"><xsl:value-of select="$className" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$methodName" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$smallMessage" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$detailedMessage" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$dateTime" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: red;">FAIL</p></td>
			  </xsl:when>			  

			  <xsl:when test="contains(text(), 'PASS')">
			    <td class="cell1Css"><p style="color: green;"><xsl:number value="position()" format="1" /></p></td>
			    <td class="cell1Css"><p style="color: green;"><xsl:value-of select="$className" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: green;"><xsl:value-of select="$methodName" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p style="color: green;"><xsl:value-of select="$smallMessage" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p style="color: green;"><xsl:value-of select="$detailedMessage" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: green;"><xsl:value-of select="$dateTime" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><a><xsl:attribute name="href"><xsl:value-of  select="$screenShot"  disable-output-escaping="yes"/></xsl:attribute><p style="color : green;"><xsl:value-of select="$logType" disable-output-escaping="yes"/></p></a></td>
			  </xsl:when>
			  
			   <xsl:when test="contains(text(), 'FAIL')">
				<td class="cell1Css"><p style="color: red;"><xsl:number value="position()" format="1" /></p></td>
			    <td class="cell1Css"><p style="color: red;"><xsl:value-of select="$className" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$methodName" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$smallMessage" disable-output-escaping="yes"/></p></td>
			 	<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$detailedMessage" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><p style="color: red;"><xsl:value-of select="$dateTime" disable-output-escaping="yes"/></p></td>
				<td class="cell1Css"><a><xsl:attribute name="href"><xsl:value-of  select="$screenShot"  disable-output-escaping="yes"/></xsl:attribute><p style="color : red;"><xsl:value-of select="$logType" disable-output-escaping="yes"/></p></a></td>
			  </xsl:when> 	
 			  
			</xsl:choose>
		</tr>
	</xsl:for-each>
</xsl:for-each>
</table>
</div>
</div>
</div>
<script type='text/javascript'>
	function deleteTableRowsAndRecreate(testCaseName){
	var testTableDiv = document.getElementById('testTableDivId');
	testTableDiv.className = 'testTableDiv';
	var allRows = document.querySelectorAll("#testTable tr.testRow");
	//var allRows = document.getElementById("testTable").getElementsByClassName("testRow");
		for(i=0;i&lt;allRows.length;i++){
			allRows[i].style.display='none';
		}
		var testTableDiv = document.getElementById('testTableDivId');
	testTableDiv.className = 'testTableDiv';
	var selectedRows = document.querySelectorAll("#testTable tr.testRow[data-id='"+testCaseName+"']");
	//var selectedRows = document.getElementById("testTable").getElementsByClassName("testRow").getElementsByAttribute("data-id", testCaseName);
		for(i=0;i&lt;selectedRows.length;i++){
			selectedRows[i].style.display='table-row';
		}
	}
</script>
</body>
</html>
</xsl:template>
</xsl:stylesheet>