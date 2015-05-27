<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>Tracker</h2>
				<xsl:apply-templates mode="navigator"/>
				<table>
					<tr>
						<td>Module</td>
						<td>Function</td>
						<td>CaseNum</td>
						<td>detail</td>
					</tr>
					<xsl:apply-templates mode="block_summary"></xsl:apply-templates>
				</table>
			</body>
		</html>
	</xsl:template>
	
	<xsl:template match="/blocks" mode="navigator">
		<xsl:for-each select="block">
			<xsl:variable name="block_name" select="@name"></xsl:variable>
			<a href="$block_name"><xsl:value-of select="@name"></xsl:value-of></a>
			<br/>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="/blocks/block" mode="block_summary">
		<xsl:for-each select="module">
			<xsl:apply-templates mode="function_summary">
				<xsl:with-param name="module_name_param" select="../@name"></xsl:with-param>
			</xsl:apply-templates>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="function" mode="function_summary">
		<xsl:param name="module_name_param"></xsl:param>
		<xsl:variable name="function_name" select="@name"></xsl:variable>
		<xsl:variable name="case_number" select="count((caseinfo/suite/group/case))"></xsl:variable>
		<tr>
			<td><xsl:value-of select="$module_name_param"></xsl:value-of></td>
			<td><xsl:value-of select="$function_name"></xsl:value-of></td>
			<td><xsl:value-of select="$case_number"></xsl:value-of></td>
			<td><a>detail</a></td>
		</tr>
	</xsl:template>
</xsl:stylesheet>