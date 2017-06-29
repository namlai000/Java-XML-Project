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
        <div class="cover-left">
            <xsl:for-each select="TblNewsHeader">
                <xsl:if test="position() = 1">
                    <div class="row">
                        <div>
                            <img src="{tblNews/tblImageList/link}" alt=""/>
                        </div>
                        <div>
                            <h2>
                                <a href="http://localhost:8084/JAVAXMLPROJECT/ProcessServlet?location=article&amp;id={id}">
                                    <xsl:value-of select="tittle"/>
                                </a>
                            </h2>
                        </div>
                        <div>
                            <xsl:value-of select="description"/>
                        </div>
                    </div>
                </xsl:if>
            </xsl:for-each>
            <xsl:if test="count(TblNewsHeader) &gt; 1">
                <table style="margin-top: 24px">
                    <tr>
                        <xsl:for-each select="TblNewsHeader">
                            <xsl:if test="position() &gt; 1 and position() &lt; 5">
                                <td>
                                    <div class="row">
                                        <div>
                                            <img src="{tblNews/tblImageList/link}" alt=""/>
                                        </div>
                                        <div>
                                            <h3>
                                                <a href="http://localhost:8084/JAVAXMLPROJECT/ProcessServlet?location=article&amp;id={id}">
                                                    <xsl:value-of select="tittle"/>
                                                </a>
                                            </h3>
                                        </div>
                                        <div>
                                            <xsl:value-of select="description"/>
                                        </div>
                                    </div>
                                </td>
                            </xsl:if>
                        </xsl:for-each>
                    </tr>
                </table>
            </xsl:if>
        </div>
        <xsl:if test="count(TblNewsHeader) &gt; 4">
            <div class="cover-right">
                <xsl:for-each select="TblNewsHeader">
                    <xsl:if test="position() = 5">
                        <div class="row">
                            <div>
                                <img src="{tblNews/tblImageList/link}" alt=""/>
                            </div>
                            <div>
                                <h4>
                                    <a href="http://localhost:8084/JAVAXMLPROJECT/ProcessServlet?location=article&amp;id={id}">
                                        <xsl:value-of select="tittle"/>
                                    </a>
                                </h4>
                            </div>
                        </div>
                    </xsl:if>
                </xsl:for-each>
                <xsl:if test="count(TblNewsHeader) &gt; 5">
                    <xsl:for-each select="TblNewsHeader">
                        <xsl:if test="position() > 5">
                            <div>
                                <h5>
                                    <a href="http://localhost:8084/JAVAXMLPROJECT/ProcessServlet?location=article&amp;id={id}">
                                        <xsl:value-of select="tittle"/>
                                    </a>
                                </h5>
                            </div>
                        </xsl:if>
                    </xsl:for-each>
                </xsl:if>
            </div>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>
