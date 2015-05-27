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

	<xsl:param name="PAGE" select="''"/>

	<xsl:template match="/blocks" mode="navigator">
		<xsl:for-each select="block">
			<xsl:variable name="block_name" select="@name"></xsl:variable>
			<a>
				<xsl:attribute name="href"><xsl:value-of select="$block_name"></xsl:value-of>.html</xsl:attribute>
				<xsl:value-of select="$block_name"></xsl:value-of>
			</a>
			<br/>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="/blocks/block[@name='oab']" mode="block_summary">
<!-- 		<xsl:variable name="module_name" select="module/@name"/>
		<xsl:apply-templates mode="function_summary">
			<xsl:with-param name="module_name_param" select="$module_name"></xsl:with-param>
		</xsl:apply-templates> -->
		<xsl:value-of select="$PAGE"></xsl:value-of>
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