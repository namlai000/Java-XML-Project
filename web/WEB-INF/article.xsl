<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="tblNewsHeader">
        <div>
            <h1>
                <xsl:value-of select="tittle"/>
            </h1>
            <h4>
                <font color="gray">
                    <img src="Images/author.png" width="20px" height="20px"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="tblNews/authorID/lastname"/><xsl:text> | </xsl:text><img src="Images/date.png" width="20px" height="20px"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="concat(substring(date, 9, 2), '-', substring(date, 6, 2), '-', substring(date, 1, 4))" />
                </font>
                <span style="float: right;" class="print">
                    <a href="ProcessServlet?location=print&amp;name=article">Print</a>
                </span>
            </h4>
        </div>
        <div style="margin-bottom: 18px;">
            <b>
                <xsl:value-of select="description"/>
            </b>
        </div>
        <div class="row">
            <xsl:for-each select="tblNews/tblImageList">
                <img name="tmp" src="{link}" alt="" style="display: none"/>   
            </xsl:for-each>
        </div>
        <div>
            <xsl:value-of select="tblNews/content" disable-output-escaping="yes"/>
        </div>
        <div class="break-line">
            <hr/>
        </div>
        <div>
            <i>
                Tags: <span>
                    <a href="ProcessServlet?location=explore&amp;menu={tblNews/catID/id}">
                        <xsl:value-of select="tblNews/catID/subCategoryName"/>
                    </a>
                </span>                   
            </i>
        </div>
        <input id="newid" type="hidden" value="{id}"/>
    </xsl:template>
</xsl:stylesheet>
