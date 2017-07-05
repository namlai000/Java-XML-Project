<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>
    <xsl:param name="pattern"/>
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="TblNewsHeaders">
        <div class="row">
            <table>
                <xsl:for-each select="TblNewsHeader[type=$pattern]">
                    <xsl:if test="type = 1">
                        <tr>
                            <td>
                                <div class="article-image">
                                    <img src="{tblNews/tblImageList/link}" onerror="this.src='Images/placeholder-blue.png'"/>
                                </div>
                                <div class="article-text">
                                    <h2>
                                        <a href="ProcessServlet?location=article&amp;id={id}">
                                            <xsl:value-of select="tittle" />
                                        </a>
                                    </h2>
                                    <h5>
                                        <xsl:value-of select="tblNews/authorID/lastname" />
                                    </h5>
                                    <xsl:value-of select="description" />
                                    <div class="row">
                                        <button type="button" class="button-green" style="float:right;" onclick="deleteArticle({id})">Delete</button>
                                        <button type="button" class="button-green" style="float:right;" onclick="editArticle({id})">Edit</button>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </xsl:if>
                    <xsl:if test="type != 1">
                        <tr>
                            <td>
                                <div class="auArticle-image">
                                    <img src="{tblNews/authorID/imageID/link}" onerror="this.src='Images/placeholder-blue.png'"/>
                                </div>
                                <div class="article-text">
                                    <h2>
                                        <a href="ProcessServlet?location=read&amp;id={id}">
                                            <xsl:value-of select="tittle" />
                                        </a>
                                    </h2>
                                    <h5>
                                        <xsl:value-of select="tblNews/authorID/lastname" />
                                    </h5>
                                    <xsl:value-of select="description" />
                                    <div class="row">
                                        <button type="button" class="button-green" style="float:right;" onclick="deleteArticle({id})">Delete</button>
                                        <button type="button" class="button-green" style="float:right;" onclick="editArticle({id})">Edit</button>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </xsl:if>
                </xsl:for-each>
            </table>
        </div>
    </xsl:template>
</xsl:stylesheet>
