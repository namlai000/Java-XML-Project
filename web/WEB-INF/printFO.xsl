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
                        <fo:inline>
                            <fo:external-graphic src="url('Images/logo.png')" content-width="120px" vertical-align="middle"/>
                        </fo:inline>
                        <fo:inline font-weight="bold">                        
                            <xsl:text>FPT Insiders</xsl:text>
                        </fo:inline>
                    </fo:block>
                    <fo:block border-bottom-width="1px" border-bottom-style="solid" border-bottom-color="black" margin-bottom="80px" margin-top="28px"></fo:block>                
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
                    <xsl:choose>
                        <xsl:when test="contains(., '&lt;p&gt;')">
                            <xsl:choose>
                                <xsl:when test="contains(., '&lt;b&gt;')">
                                    <fo:block space-before="3mm" font-weight="bold">
                                        <xsl:value-of select="substring-after(substring-before(., '&lt;/b&gt;'), '&lt;b&gt;')"/>
                                    </fo:block>
                                </xsl:when>
                                <xsl:when test="contains(., '&lt;/iframe&gt;')">
                                    <xsl:variable name="imagefromyt" select="substring-after(substring-before(., '?autoplay=1&quot; frameborder=&quot;0&quot; allowfullscreen&gt;&lt;/iframe&gt;'), '&lt;p&gt;&lt;iframe type=&quot;text/html&quot; width=&quot;640&quot; height=&quot;360&quot; src=&quot;https://www.youtube.com/embed/')"/>
                                    <fo:block space-before="3mm" font-weight="bold">
                                        <fo:external-graphic src="url('http://img.youtube.com/vi/{$imagefromyt}/0.jpg')" width="100%" content-height="100%" content-width="scale-to-fit" scaling="uniform"/>
                                    </fo:block>
                                </xsl:when>
                                <xsl:otherwise>
                                    <fo:block space-before="3mm">
                                        <xsl:value-of select="substring-after(., '&lt;p&gt;')"/>
                                    </fo:block>
                                </xsl:otherwise>
                            </xsl:choose> 
                        </xsl:when>
                        <xsl:otherwise>
                            <fo:block space-before="3mm">
                                <xsl:value-of select="."/>
                            </fo:block>
                        </xsl:otherwise>
                    </xsl:choose>
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
