<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
    <xsl:param name="url"/>
    <xsl:template match="tblNewsHeader">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="one" page-height="8.5in"
                                       page-width="11in" margin-top="0.5in" margin-bottom="0.5in"
                                       margin-left="1in" margin-right="1in">
                    <fo:region-body margin-top="0.5in"/>
                    <fo:region-before extent="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="one">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Segoe UI" font-size="24px">
                        <xsl:value-of select="tittle"/>
                    </fo:block>
                    <fo:block font-family="Segoe UI" margin-top="12px">               
                        <xsl:value-of select="tblNews/authorID/lastname"/> | <xsl:value-of select="concat(substring(date, 9, 2), '-', substring(date, 6, 2), '-', substring(date, 1, 4))" />
                    </fo:block>
                    <fo:block font-family="Segoe UI" font-weight="bold" margin-top="16px">               
                        <xsl:value-of select="description"/>
                    </fo:block>
                    <fo:table inline-progression-dimension="auto" table-layout="auto">
                        <fo:table-body>
                            <fo:table-row>
                                <xsl:for-each select="tblNews/tblImageList">
                                    <fo:table-cell padding-left="8px" padding-right="8px">
                                        <fo:block margin-top="16px">         
                                            <fo:external-graphic src="url({link})" width="100%" content-height="100%" content-width="scale-to-fit" scaling="uniform"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </xsl:for-each>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <fo:block font-family="Segoe UI" margin-top="16px">               
                        <xsl:value-of select="tblNews/content"/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
