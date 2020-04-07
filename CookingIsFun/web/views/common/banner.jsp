<%-- 
    Document   : banner
    Created on : Apr 8, 2020, 1:15:07 AM
    Author     : huynh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="main-banner">
    <div class="container">
        <div class="col-md-10 offset-md-1">
            <div id="main_banner_widget-1" class="widget widget_main_banner_widget">
                <div class="caption text-center cl-white">
                    <h2>Với hơn 10.000 công thức nấu ăn</h2>
                    <p>Tìm và chia sẻ cảm hứng nấu ăn hàng ngày trên <strong>Cooking is Fun</strong>. 
                        Khám phá công thức nấu ăn, đầu bếp, video và cách làm dựa trên thực phẩm bạn yêu thích và những người bạn bạn theo dõi.
                    </p>
                </div>
            </div>
            <div class="main-search-form">
                <div id="filter_rent_sale_widget-1" class="widget widget_filter_rent_sale_widget">
                    <div class="recipe-tab active" id="filter_rent_sale_widget-1-recipe">
                        <form class="home-form-1 style1" method="get" 
                              action="FrontController">
                            <fieldset>
                                <div class="row">
                                    <div class="field col-md-9">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <input type="text" name="keySearch"
                                                       class="form-control"
                                                       placeholder="Tìm kiếm công thức nấu ăn theo thành phần, món ăn hoặc từ khóa" 
                                                       value="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="field col-md-3">
                                        <div class="form-group">
                                            <button class="btn btn-success btn-block" name="action" type="submit" value="Search">
                                                <i class="fa fa-search"></i>
                                                Tìm kiếm
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
