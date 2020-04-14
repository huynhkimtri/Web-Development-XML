<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:ns="http://www.cookingisfun.com/XMLSchema/dev" 
                version="1.0">
    <xsl:output method="xml" encoding="UTF-8"/>
    <xsl:param name="pathFile" select="'test'"/>
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" 
                 font-family="Arial">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="hello" page-height="11in" 
                                       page-width="8.5in" margin-top="1in" margin-bottom="1in" 
                                       margin-left="1in" margin-right="1in">
                    <fo:region-body margin-top="1in" margin-bottom=".5in"/>
                    <fo:region-before extent="18pt" background-color="silver"/>
                    <fo:region-after extent="12pt" background-color="silver"/>
                </fo:simple-page-master>
            </fo:layout-master-set>	
            <fo:page-sequence master-reference="hello">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block font-size="18pt" font-weight="bold"  font-family="Arial">
                        <xsl:value-of select="//*[local-name()='title']"/>
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block font-size="12pt" text-align="right">
                        Page <fo:page-number/> of <fo:page-number-citation ref-id="last-page"/>
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="14pt" text-align="left"  font-family="Arial">
                        <xsl:value-of select="//*[local-name()='description']"/>
                    </fo:block>
                    <fo:block>
                        <fo:external-graphic src="ns:image">
                            <!--<fo:external-graphic src="//*[local-name()='image']">-->
                        </fo:external-graphic>
                    </fo:block>
                    <fo:block font-size="12pt" text-align="center" >
                        <fo:table border-collapse="separate" table-layout="fixed" width="100%">
                            <fo:table-column column-width="6cm"/>
                            <fo:table-column column-width="6cm"/>
                            <fo:table-column column-width="6cm"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block text-align="left"  font-family="Arial"> 
                                            Khẩu phần cho <xsl:value-of select="//*[local-name()='ration']"/> người
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block text-align="left"  font-family="Arial">
                                            Chuẩn bị <xsl:value-of select="//*[local-name()='preparetime']"/> phút
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block text-align="left"  font-family="Arial">
                                            Thực hiện <xsl:value-of select="//*[local-name()='preparetime']"/> phút
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    
                    <fo:block font-size="18pt" text-align="center">
                        Nguyên liệu cần chuẩn bị
                    </fo:block>
                    <fo:block font-size="12pt" text-align="center" >
                        <fo:table border-collapse="separate" table-layout="fixed" width="100%">
                            <fo:table-column column-width="6cm"/>
                            <fo:table-column column-width="6cm"/>
                            <fo:table-column column-width="6cm"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell border-color="blue"
                                                   border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center">Số lượng</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue"
                                                   border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center">Đơn vị</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue"
                                                   border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center">Thực phẩm</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <xsl:for-each select="ns:recipe/ns:ingredientmenu/ns:ingredientdetail">
                                    <fo:table-row>
                                        <fo:table-cell border-color="blue"
                                                       border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center" font-family="Arial">
                                                <xsl:value-of select="ns:quantitive"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue"
                                                       border-width="0.5pt" border-style="solid" >
                                            <fo:block text-align="center" font-family="Arial">
                                                <xsl:value-of select="ns:unit"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue"
                                                       border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center" font-family="Arial">
                                                <xsl:value-of select="ns:name"/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block font-size="18pt" text-align="center">
                        Hướng dẫn nấu
                    </fo:block>
                    <fo:block font-size="12pt" text-align="center" >
                        <fo:table border-collapse="separate" table-layout="fixed" width="100%">
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="12cm"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell border-color="blue"
                                                   border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center">Bước</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue"
                                                   border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center">Chi tiết</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <xsl:for-each select="ns:recipe/ns:instructionmenu/ns:instructiondetail" >
                                    <fo:table-row>
                                        <fo:table-cell border-color="blue"
                                                       border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center" font-family="Arial">
                                                <xsl:value-of select="ns:numstep" />
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue"
                                                       border-width="0.5pt" border-style="solid" >
                                            <fo:block text-align="left" font-family="Arial">
                                                <xsl:value-of select="ns:detail"/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block id="last-page"/>
                </fo:flow>
            </fo:page-sequence>	
        </fo:root>
    </xsl:template>
</xsl:stylesheet>