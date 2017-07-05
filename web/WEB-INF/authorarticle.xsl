<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>
    <xsl:template match="/">
        <xsl:apply-templates/>            
    </xsl:template>
    <xsl:template match="tblNewsHeader">
        <div class="auArticle-image">
            <img src="{tblNews/authorID/imageID/link}"/> 
        </div>
        <div class="auArticle-text">
            <h2>
                <xsl:value-of select="tittle"/>
            </h2>
            <h3>
                <a href="ProcessServlet?location=detail&amp;id={tblNews/authorID/userId}">
                    <xsl:value-of select="tblNews/authorID/lastname"/>
                </a>
                <span style="float: right;" class="print">
                    <a href="ProcessServlet?location=print&amp;name=authorarticle">Print</a>
                </span>
            </h3>
            <hr/>
            <xsl:value-of select="description"/>
            <br/>
            <xsl:value-of select="tblNews/content" disable-output-escaping="yes"/>
        </div>
    </xsl:template>
</xsl:stylesheet>
