<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="TblUserInfos">
        <div class="row">
            <table>
                <xsl:for-each select="TblUserInfo">
                    <tr>
                        <td>
                            <div class="article-image avatar">
                                <img src="{imageID/link}" onerror="this.src='Images/placeholder-blue.png'"/>
                            </div>
                            <div class="article-text">
                                <h3>
                                    <a href="ProcessServlet?location=detail&amp;id={userId}"><xsl:value-of select="lastname" /></a>
                                </h3>
                            </div>
                        </td>
                    </tr>
                </xsl:for-each>
            </table>
        </div>
    </xsl:template>
</xsl:stylesheet>
