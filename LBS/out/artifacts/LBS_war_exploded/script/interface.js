
var user;
function login(type) {
    user = type;
    initPage();
}

function initPage() {
    initSideBar();

    Metronic.init(); // init metronic core componets
    Layout.init(); // init layout
    QuickSidebar.init(); // init quick sidebar
    // Demo.init(); // init demo features
    Index.init();
    // Index.initDashboardDaterange();
    // Index.initJQVMAP(); // init index page's custom scripts
    // Index.initCalendar(); // init index page's custom scripts
    // Index.initCharts(); // init index page's custom scripts
    // Index.initChat();
    // Index.initMiniCharts();
    // Tasks.initDashboardWidget();
}

function initSideBar() {
    $.post('/MenuList?user='+user, function(data) {
        var rootMenu = JSON.parse(data);
        var menus = rootMenu.childs;
        var html = '<li class="sidebar-toggler-wrapper">\n' +
            '\t<div class="sidebar-toggler">\n' +
            '\t</div>\n' +
            '</li>\n' +
            '<li class="sidebar-search-wrapper">\n' +
            '\t<form class="sidebar-search " action="extra_search.html" method="POST">\n' +
            '\t\t<a href="javascript:;" class="remove">\n' +
            '\t\t<i class="icon-close"></i>\n' +
            '\t\t</a>\n' +
            '\t\t<div class="input-group">\n' +
            '\t\t\t<input type="text" class="form-control" placeholder="Search...">\n' +
            '\t\t\t<span class="input-group-btn">\n' +
            '\t\t\t<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>\n' +
            '\t\t\t</span>\n' +
            '\t\t</div>\n' +
            '\t</form>\n' +
            '</li>';
        for (var i in menus) {
            html += parse_menu(menus[i], 1);
        }
        document.getElementById('page-sidebar-menu').innerHTML = html;
    });
}

function parse_menu(node, depth) {
    if (node.type !== 'menu') {
        return '';
    }
    var html = '';
    var modifier = '';
    if (node.htmlId) {
        modifier += 'id="' + node.html_id + '" ' ;
    }
    if (node.class) {
        modifier += 'class="' + node.class + '"';
    }
    html += '<li ' + modifier + '>';
    var href = node.href?node.href:'javascript:;';
    html += '<a href="' + href + '">';
    if (node.icon) {
        html += '<i class="' + node.icon + '"></i>';
    }
    if (depth === 1) {
        html += '<span class="title">' + node.name + '</span>';
    } else {
        html += node.name;
    }
    if (node.childs) {
        html += '<span class="arrow"></span></a>'
        html += '<ul class="sub-menu">';
        for (var i = 0; i < node.childs.length; i++) {
            html += parse_menu(node.childs[i], depth + 1);
        }
        html += '</ul>';
    } else {
        html += '</a>';
    }
    html += '</li>';
    return html;
}



