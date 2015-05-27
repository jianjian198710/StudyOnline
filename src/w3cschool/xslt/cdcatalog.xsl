<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>My CD Collection</h2>			
				<table border="1">
					<tr bgcolor="#9acd32">
						<th align="left">Title</th>
						<th align="left">Aritist</th>
					</tr>
					<xsl:for-each select="catalog/cd">
						<tr>
							<td><xsl:value-of select="title"></xsl:value-of></td>
							<xsl:choose>
								<xsl:when test="price &gt; 10">
								<td bgcolor="#ff00ff">
									<xsl:value-of select="artist"></xsl:value-of>
								</td>
								</xsl:when>
								<xsl:otherwise>
								<td>
									<xsl:value-of select="artist"></xsl:value-of>
								</td>
								</xsl:otherwise>
							</xsl:choose>			
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>