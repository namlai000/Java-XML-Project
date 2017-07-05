<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:str="http://exslt.org/strings"
                version="1.0">
    <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
    <xsl:param name="url"/>
    <xsl:template match="tblNewsHeader">
        <fo:root>
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
                    <fo:block font-family="Segoe UI" margin-top="16px"> 
                        <xsl:apply-templates select="tblNews"/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    <xsl:template match="tblNews">
        <xsl:call-template name="parse">
            <xsl:with-param name="text" select="content"/>
            <xsl:with-param name="list" select="tblImageList"/>
        </xsl:call-template> 
    </xsl:template>
    <xsl:template name="parse">
        <xsl:param name="text"/>
        <xsl:param name="list"/>
        <!-- Seperator -->
        <xsl:for-each select="str:split($text, '&lt;img name=&quot;image&quot; src=&quot;none&quot; class=&quot;ar-image&quot;/&gt;')">
            <xsl:variable name="count" select="position()"/>
            <xsl:for-each select="str:split(., '&lt;/p&gt;')">
                <xsl:for-each select="str:split(., '&lt;br/&gt;')">
                    <fo:block space-before="3mm">
                        <xsl:choose>
                            <xsl:when test="contains(., '&lt;p&gt;')">
                                <xsl:value-of select="substring-after(., '&lt;p&gt;')"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:value-of select="."/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </fo:block>
                </xsl:for-each>
            </xsl:for-each>
            <fo:block margin-top="16px">
                <xsl:for-each select="$list">
                    <xsl:if test="position() = $count">
                        <fo:external-graphic src="url('{link}')" width="100%" content-height="100%" content-width="scale-to-fit" scaling="uniform"/>
                    </xsl:if>
                </xsl:for-each>
            </fo:block>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
