<%--
  Created by IntelliJ IDEA.
  User: charlieivie
  Date: 31/01/2012
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Jo Ivie Arts</title>
    <link rel="stylesheet" href="/css/basic.css" type="text/css"/>
    <link rel="stylesheet" href="/css/galleriffic-5.css" type="text/css"/>
    <link href='http://fonts.googleapis.com/css?family=Amatic+SC:400,700' rel='stylesheet' type='text/css'>
    <!--[if gte IE 9]>
    <style type="text/css">
        .gradient {
            filter: none;
        }
    </style>
    <![endif]-->
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.history.js"></script>
    <script type="text/javascript" src="/js/jquery.galleriffic.js"></script>
    <script type="text/javascript" src="/js/jquery.opacityrollover.js"></script>
    <!-- We only want the thunbnails to display when javascript is disabled -->
    <script type="text/javascript">
        document.write('<style>.noscript { display: none; }</style>');
    </script>
</head>
<body>

<div id="page">
<div id="container">
<div id="header">
    <h1><a href="index.html">Jo Ivie Arts</a></h1>
    <ul class="topnav">
        <li><a href="#">About</a></li>
        <li><a href="#">Contact</a></li>
    </ul>
</div>

<div class="left">
    <jsp:include page="/art/categories"/>
</div>
<div class="main">
<!-- Start Advanced Gallery Html Containers -->

<div class="content">
    <div class="slideshow-container">
        <div id="controls" class="controls"></div>

        <div id="loading" class="loader"></div>
        <div id="slideshow" class="slideshow"></div>
    </div>
    <div id="caption" class="caption-container">
        <div class="photo-index"></div>
    </div>
</div>

<div class="navigation-container">
<div id="thumbs" class="navigation">

<a class="pageLink prev" style="visibility: hidden;" href="#" title="Previous Page"></a>

<jsp:include page="/art/images/${categoryId!=null?categoryId:0}"/>

<a class="pageLink next" style="visibility: hidden;" href="#" title="Next Page"></a></div>
</div>
<!-- End Gallery Html Containers -->
</div>
<div class="clear">&nbsp;</div>

</div>
</div>
<div id="footer">&copy; </div>
<script type="text/javascript">
    jQuery(document).ready(function ($) {
        // We only want these styles applied when javascript is enabled
        $('div.content').css('display', 'block');

        // Initially set opacity on thumbs and add
        // additional styling for hover effect on thumbs
        var onMouseOutOpacity = 0.67;
        $('#thumbs ul.thumbs li, div.navigation a.pageLink').opacityrollover({
            mouseOutOpacity:onMouseOutOpacity,
            mouseOverOpacity:1.0,
            fadeSpeed:'fast',
            exemptionSelector:'.selected'
        });

        // Initialize Advanced Galleriffic Gallery
        var gallery = $('#thumbs').galleriffic({
            delay:2500,
            numThumbs:7,
            preloadAhead:7,
            enableTopPager:false,
            enableBottomPager:false,
            imageContainerSel:'#slideshow',
            controlsContainerSel:'#controls',
            captionContainerSel:'#caption',
            loadingContainerSel:'#loading',
            renderSSControls:true,
            renderNavControls:true,
            playLinkText:'Play Slideshow',
            pauseLinkText:'Pause Slideshow',
            prevLinkText:'&lsaquo; Previous Photo',
            nextLinkText:'Next Photo &rsaquo;',
            nextPageLinkText:'Next &rsaquo;',
            prevPageLinkText:'&lsaquo; Prev',
            enableHistory:true,
            autoStart:false,
            syncTransitions:true,
            defaultTransitionDuration:900,
            onSlideChange:function (prevIndex, nextIndex) {
                // 'this' refers to the gallery, which is an extension of $('#thumbs')
                this.find('ul.thumbs').children()
                        .eq(prevIndex).fadeTo('fast', onMouseOutOpacity).end()
                        .eq(nextIndex).fadeTo('fast', 1.0);

                // Update the photo index display
                this.$captionContainer.find('div.photo-index')
                        .html('Photo ' + (nextIndex + 1) + ' of ' + this.data.length);
            },
            onPageTransitionOut:function (callback) {
                this.fadeTo('fast', 0.0, callback);
            },
            onPageTransitionIn:function () {
                var prevPageLink = this.find('a.prev').css('visibility', 'hidden');
                var nextPageLink = this.find('a.next').css('visibility', 'hidden');

                // Show appropriate next / prev page links
                if (this.displayedPage > 0)
                    prevPageLink.css('visibility', 'visible');

                var lastPage = this.getNumPages() - 1;
                if (this.displayedPage < lastPage)
                    nextPageLink.css('visibility', 'visible');

                this.fadeTo('fast', 1.0);
            }
        });

        /**************** Event handlers for custom next / prev page links **********************/

        gallery.find('a.prev').click(function (e) {
            gallery.previousPage();
            e.preventDefault();
        });

        gallery.find('a.next').click(function (e) {
            gallery.nextPage();
            e.preventDefault();
        });

        /****************************************************************************************/

        /**** Functions to support integration of galleriffic with the jquery.history plugin ****/

            // PageLoad function
            // This function is called when:
            // 1. after calling $.historyInit();
            // 2. after calling $.historyLoad();
            // 3. after pushing "Go Back" button of a browser
        function pageload(hash) {
            // alert("pageload: " + hash);
            // hash doesn't contain the first # character.
            if (hash) {
                $.galleriffic.gotoImage(hash);
            } else {
                gallery.gotoIndex(0);
            }
        }

        // Initialize history plugin.
        // The callback is called at once by present location.hash.
        $.historyInit(pageload, "advanced.html");

        // set onlick event for buttons using the jQuery 1.3 live method
        $("a[rel='history']").live('click', function (e) {
            if (e.button != 0) return true;

            var hash = this.href;
            hash = hash.replace(/^.*#/, '');

            // moves to a new page.
            // pageload is called at once.
            // hash don't contain "#", "?"
            $.historyLoad(hash);

            return false;
        });

        /****************************************************************************************/
    });
</script>
</body>
</html>