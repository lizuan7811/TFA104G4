<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">   
    <link href="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/shop_cf.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/header&footer.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a3a545912b.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="slick-1.8.1\slick\slick.css" />
    <link rel="stylesheet" href="slick-1.8.1\slick\slick-theme.css" />
   	<title>食健商城</title>

</head>

<body>
    <header>
        <div class="logo">
            <a href="">
                <img src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/食健logo.png" alt="logo">
            </a>
        </div>

        <ul class="header_li">
            <li>
                <a class="menu_title" href="#">關於我們</a>
            </li>
            <li>
                <a class="menu_title" href="">食健商城</a>
             
            </li>
            <li>
                <a class="menu_title" href="#">食健生活</a>
            </li>
            <li>
                <a class="menu_title" href="#">會員登入</a>
            </li>
        </ul>

        <div class="icon">
            <div class="search_icon"><i class="fas fa-search"></i></div>
            <div class="QA_icon"><i class="far fa-question-circle"></i></div>
             <!-- 加入購物車 icon -->
            <form name="checkoutForm" action="<%=request.getContextPath()%>/CartServlet" method="POST">
            <input class="cart_icon" type="submit" name="Submit" value="&#xf07a" id="submitButton">
            <input type="hidden" name="action"	value="CHECKOUT"> 
            <span class="cart-counter">0</span> 
           	</form>
        </div>
    </header>

    <main>

        <div class="shop_bg">
            <img id="shop_bg_img" src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/shopbackground.jpg">
        </div>

        <div class="top">
            <div class="top_item" id="top1">
                <div id="product_img1" class="top_box1">
                   
                </div>
                <div class="top_box2" id="top1_box2">
                    <div id="product_name1" class="top_product"></div>
                    <div id="product_price1" class="top_price"><span id="product_unit1" class="product_unit"></span></div>
			

                </div>
<form name="shoppingForm" action="<%=request.getContextPath()%>/CartServlet" method="POST">
                <div class="top_box3" id="top1_box3">
                	<input class="top_cart_icon pointer" type="submit" name="Submit" value="&#xf07a" id="submitButton">
                </div>
                <div class="top_box4"><a href="<%=request.getContextPath()%>/front_end/cart/buyproduct.jsp">TOP.1</a></div>
            <input type="hidden" name="name" value="舒肥雞胸肉">
      		<input type="hidden" name="descrip" value="低溫舒肥，鮮嫩多汁不乾柴。">
      		<input type="hidden" name="price" value="79"> 
      		<input type="hidden" name="quantity" size="1" value=1>            
            <input type="hidden" name="action" value="ADD">	
            </div>
</form>
            <div class="top_item" id="top2">
                <div class="top_item" id="top1">
                    <div id="product_img2" class="top_box1">
                        
                    </div>
                    <div class="top_box2" id="top2_box2">
                        <div id="product_name2" class="top_product"></div>
                        <div id="product_price2" class="top_price"><span id="product_unit2" class="product_unit"></span></div>
						

                    </div>
 <form name="shoppingForm" action="<%=request.getContextPath()%>/CartServlet" method="POST">
                    <div class="top_box3" id="top2_box3">
                        <input class="top_cart_icon pointer" type="submit" name="Submit" value="&#xf07a" id="submitButton">
                    </div>
                    <div class="top_box4">TOP.2</div>
                </div>
            <input type="hidden" name="name" value="鮭魚切片">
      		<input type="hidden" name="descrip" value="鮭魚含有豐富的Omega-3脂肪酸，能降低三酸甘油酯，DHA則對大腦與眼睛有益處。">
      		<input type="hidden" name="price" value="179">  
      		<input type="hidden" name="quantity" size="1" value=1>          
            <input type="hidden" name="action" value="ADD">	
            </div>
</form>
            <div class="top_item" id="top3">
                <div class="top_item" id="top1">
                    <div id="product_img3" class="top_box1">
                        
                    </div>
                    <div class="top_box2" id="top3_box2">
                        <div id="product_name3" class="top_product"></div>
                        <div id="product_price3" class="top_price"><span id="product_unit3" class="product_unit"></span></div>
                        

 <form name="shoppingForm" action="<%=request.getContextPath()%>/CartServlet" method="POST">
                    </div>
                    <div class="top_box3" id="top3_box3">
                        <input class="top_cart_icon pointer" type="submit" name="Submit" value="&#xf07a" id="submitButton">
                    </div>
                    <div class="top_box4">TOP.3</div>
                </div>
            <input type="hidden" name="name" value="高麗菜">
      		<input type="hidden" name="descrip" value="高麗菜含有維生素B群、維生素C、維生素K、維生素U、鈣、磷、鉀、有機酸、膳食纖維等營養素，其中鈣、鐵、磷的含量在各類蔬菜中名列前五名。">
      		<input type="hidden" name="price" value="60">  
      		<input type="hidden" name="quantity" size="1" value=1>          
            <input type="hidden" name="action" value="ADD">
            </div>
        </div>
</form>
        <div class="resipe">
            <div class="hotResipe pointer" onclick="location.href='hotRecipe.html'">
                <div class="hotResipe_box1">熱門食譜</div>
                <div class="hotResipe_box2"><img id="hotResipe_img" src="img/木板.jpg"></div>
            </div>

            <div class="resipe_item" id="resipe1">
                <div class="resipe_box1 pointer">
                    <img class="resipe_img" src="img/香草烤雞胸.jpg">
                </div>
                <div class="resipe_box2" id="resipe1_box2">
                    <div class="resipe_name">香草烤雞胸</div>
                    <div class="resipe_list">
                        <ul>
                            <li>雞胸肉1片</li>
                            <li>番茄2顆</li>
                            <li>洋蔥1顆</li>
                        </ul>
                    </div>
                    <div class="resipe_cart_icon pointer"><i class="fas fa-shopping-cart"></i></div>
                </div>
                <div class="resipe_box3" id="resipe1_box3"></div>
            </div>

            <div class="resipe_item" id="resipe2">
                <div class="resipe_box1">
                    <img class="resipe_img" src="img/番茄烘蛋.jpg">
                </div>
                <div class="resipe_box2" id="resipe1_box2">
                    <div class="resipe_name">>番茄烘蛋</div>
                    <div class="resipe_list">
                        <ul>
                            <li>番茄2顆</li>
                            <li>蛋2顆</li>
                        </ul>
                    </div>
                    <div class="resipe_cart_icon pointer"><i class="fas fa-shopping-cart"></i></div>
                </div>
                <div class="resipe_box3" id="resipe2_box3"></div>
            </div>

            <div class="resipe_item" id="resipe3">
                <div class="resipe_box1">
                    <img class="resipe_img" src="img/紙包鮭魚.jpg">
                </div>
                <div class="resipe_box2" id="resipe1_box2">
                    <div class="resipe_name">紙包鮭魚</div>
                    <div class="resipe_list">
                        <ul>
                            <li>鮭魚切片1片</li>
                            <li>大蒜1顆</li>
                            <li>紅洋蔥1顆</li>
                            <li>檸檬1顆</li>
                        </ul>
                    </div>
                    <div class="resipe_cart_icon pointer"><i class="fas fa-shopping-cart"></i></div>
                </div>
                <div class="resipe_box3" id="resipe3_box3"></div>
            </div>
        </div>

        <div class="sort">
            <div class="sort_item" id="sort1">
                <div class="sort_bar">
                    <div class="sort_line" id="sort1_left"></div>
                    <div class="sort_name" id="sort1_name">肉類</div>
                    <div class="sort_line" id="sort1_right"></div>
                </div>
                <div class="sort_imgs" data-slick='{"slidesToShow": 4, "slidesToScroll": 4}'>
                    <div class="your-class">
                        <div><img src="product/肉類/台灣安心草蝦/台灣安心草蝦.jpg">
                            <h3>台灣安心草蝦</h3>
                        </div>
                        <div><img src="product/肉類/挪威薄鹽鯖魚片/挪威薄鹽鯖魚片.jpg">
                            <h3>挪威薄鹽鯖魚片</h3>
                        </div>
                        <div><img src="product/肉類/挪威鮭魚切片/挪威鮭魚切片.jpeg">
                            <h3>挪威鮭魚切片</h3>
                        </div>
                        <div><img src="product/肉類/美國嚴選牛腩切塊/美國嚴選牛腩切塊.jpg">
                            <h3>美國嚴選牛腩切塊</h3>
                        </div>
                        <div><img src="product/肉類/高級去骨雞腿排/高級去骨雞腿排.jpg">
                            <h3>高級去骨雞腿排</h3>
                        </div>
                        <div><img src="product/肉類/高級豬里肌肉/高級豬里肌肉.jpg">
                            <h3>高級豬里肌肉</h3>
                        </div>
                    </div>
                </div>
            </div>

            <div class="sort_item" id="sort2">
                <div class="sort_bar">
                    <div class="sort_line" id="sort2_left"></div>
                    <div class="sort_name" id="sort2_name">菜類</div>
                    <div class="sort_line" id="sort2_right"></div>
                </div>
                <div class="sort_imgs">
                    <div class="your-class">
                        <div><img src="product/菜類/大坑鮮香菇/大坑鮮香菇.jpg">
                            <h3>大坑鮮香菇</h3>
                        </div>
                        <div><img src="product/菜類/恆春有機洋蔥/恆春有機洋蔥.jpg">
                            <h3>恆春有機洋蔥</h3>
                        </div>
                        <div><img src="product/菜類/斗南馬鈴薯/斗南馬鈴薯.jpg">
                            <h3>斗南馬鈴薯</h3>
                        </div>
                        <div><img src="product/菜類/機金時栗子地瓜/有機金時栗子地瓜.jpg">
                            <h3>有機金時栗子地瓜</h3>
                        </div>
                        <div><img src="product/菜類/韓國櫛瓜/韓國櫛瓜.jpg">
                            <h3>韓國櫛瓜</h3>
                        </div>
                        <div><img src="product/菜類/有機高山高麗菜/有機高山高麗菜.jpg">
                            <h3>有機高山高麗菜</h3>
                        </div>
                    </div>

                </div>
            </div>
            <div class="sort_item" id="sort3">
                <div class="sort_bar">
                    <div class="sort_line" id="sort3_left"></div>
                    <div class="sort_name" id="sort3_name">水果</div>
                    <div class="sort_line" id="sort3_right"></div>
                </div>
                <div class="sort_imgs">
                    <div class="your-class">
                        <div><img src="product/水果/台灣在地酪梨/台灣在地酪梨.jpg">
                            <h3>台灣在地酪梨</h3>
                        </div>
                        <div><img src="product/水果/大湖草莓/大湖草莓.jpg">
                            <h3>大湖草莓</h3>
                        </div>
                        <div><img src="product/水果/旗山黃檸檬/旗山黃檸檬.JPG">
                            <h3>旗山黃檸檬</h3>
                        </div>
                        <div><img src="product/水果/有機牛蕃茄/有機牛蕃茄.jpg">
                            <h3>有機牛蕃茄</h3>
                        </div>
                        <div><img src="product/水果/東勢橘子/東勢橘子.jpg">
                            <h3>東勢橘子</h3>
                        </div>
                        <div><img src="product/水果/紐西蘭奇異果/紐西蘭奇異果.jpg">
                            <h3>紐西蘭奇異果</h3>
                        </div>

                    </div>



                </div>

            </div>

    </main>

    <footer>
        <section class="myfooter">
            <div class="contact_info">
                <img src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/食健logo.png" alt="logo">
                <div class="box_1">連絡電話:0800-000-000</div>
                <div class="box_1">聯絡地址:台北市南京復興</div>
                <div class="box_1">E-MAIL:abc@123.com</div>
            </div>
            <div id="all_rights_reserve">©2021 食健 All Rights Reserve.</div>
        </section>
    </footer>

    <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_vendors/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="slick-1.8.1\slick\slick.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_js/shop.js"></script>

</body>

</html>