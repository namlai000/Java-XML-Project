<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="TblNewsHeaders">
        <div class="row">
            <table>
                <xsl:for-each select="TblNewsHeader">
                    <tr>
                        <td>
                            <div class="article-image">
                                <img src="{tblNews/tblImageList/link}" />
                            </div>
                            <div class="article-text">
                                <h2>
                                    <a href="ProcessServlet?location=article&amp;id={id}"><xsl:value-of select="tittle" /></a>
                                </h2>
                                <h5><xsl:value-of select="TblNews/authorID/lastname" /></h5>
                                <xsl:value-of select="description" />
                            </div>
                        </td>
                    </tr>
                </xsl:for-each>
            </table>
        </div>
    </xsl:template>
</xsl:stylesheet>
