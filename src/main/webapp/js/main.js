/**
 * Created by Administrator on 15-1-10.
 */
function CCApp() {
    if (typeof CCApp.instance === "object") {
        return CCApp.instance;
    }
    CCApp.instance = this;
    this.initMenu = fnInitNavMenu;// 初始化左边菜单
    this.addTab = fnAddTab;// 添加标签页:opt{title,url,[icon]}
    this.closeTab = fnCloseTab;// 关闭Tab:title
    this.selectTab = fnSelectTab;// 选中Tab：title
    this.switchMenu = fnSwitchMenu;// 切换功能块
    this.init = fnInit;
    this.menus = {};

    var ui = {
        navigator : $("body").layout('panel', 'west'),
        menu : $("#wnav"),
        main : $("#tabs"),
        tabMenu : $("#tabMenu"),
        tabHeader : $(".tabs-inner"),
        menuItems : []
    };

    function fnInit() {
        _initTabs();
    }

    /*******************************************************************************************************************
     * 初始化导航菜单相关 *
     ******************************************************************************************************************/
    function fnInitNavMenu(menuData) {
        $.each(menuData, function(i, m) {
            var _opt = {
                title : m.title,
                content : _initMenuItems(m.items),
                iconCls : m.icon || 'icon icon-nav',
                animate : false
            };
            ui.menu.accordion('add', _opt);
        });
        ui.menu.accordion('select', 0);
        ui.menu.accordion({
            animate : false
        });
        $.extend(ui, {
            menuItems : $(".easyui-accordion ul li")
        });
        ui.menuItems.on("click", _MenuItemClick);
    }

    function fnSwitchMenu(m, tar) {
        var _panels = ui.menu.accordion("panels");
        for (var i = _panels.length; i > 0; i--) {
            ui.menu.accordion("remove", 0);
        }
        $('#css3menu a').removeClass('active');
        $(tar).addClass('active');
        $('.layout-panel-west>.panel-header>.panel-title').html(m);
        _closeAllTab();
        fnInitNavMenu(window.CC.menus[m]);
    }

    function _initMenuItems(items) {
        var list = '<ul>';
        $.each(items, function(i, it) {
            if (!it.icon)
                it.icon = "icon icon-nav";
            list += '<li cc-option="{title:\'' + it.title;
            list += '\',url:\'' + it.url;
            list += '\',icon:\'' + it.icon + '\'}">';
            list += '<div><span class="icon ' + it.icon + '">&nbsp;</span>';
            list += '<span>' + it.title + '</span></div></li>';
        });
        list += '</ul>';
        return list;
    }

    function _MenuItemClick() {
        var _optStr = $(this).attr("cc-option");
        var _opt = eval("(" + _optStr + ")");
        fnAddTab(_opt);
    }

    /*******************************************************************************************************************
     * Tab页面相关 *
     ******************************************************************************************************************/
    function fnAddTab(opt) {
        var _opt = _mixTabOptions(opt);
        if (!ui.main.tabs('exists', _opt.title)) {
            ui.main.tabs('add', _opt);
            _initTabRightClick();
        } else {
            var _tab = ui.main.tabs('getTab', _opt.title);
            ui.main.tabs('select', _opt.title);
            fnUpdateTab(_tab, _opt);
        }
    }

    function fnUpdateTab(tab, opt) {
        var _opt = _mixTabOptions(opt);
        ui.main.tabs('update', {
            tab : tab,
            options : _opt
        });
    }

    function fnCloseTab(title) {
        ui.main.tabs('close', title);
    }

    function fnSelectTab(title) {
        ui.main.tabs('select', title);
    }

    function _mixTabOptions(opt) {
        if (!opt.icon)
            opt.icon = "icon icon-nav";
        $.extend(opt, {
            content : '<iframe scrolling="auto" frameborder="0"  src="' + opt.url + '" class="cc-frame"></iframe>',
            closable : true
        });
        return opt;
    }

    function _initTabRightClick() {
        $.extend(ui, {
            tabHeader : $(".tabs-inner")
        });
        ui.tabHeader.on('contextmenu', function(e) {
            ui.tabMenu.menu('show', {
                left : e.pageX,
                top : e.pageY
            });
            var title = $(this).text();
            ui.main.tabs('select', title);
            return false;
        });
    }

    function _initTabRightClickMenu() {
        var tabMenuUI = {
            tabupdate : _get("#mm-tabupdate"),
            tabclose : _get("#mm-tabclose"),
            tabcloseall : _get("#mm-tabcloseall"),
            tabcloseother : _get("#mm-tabcloseother"),
            tabcloseright : _get("#mm-tabcloseright"),
            tabcloseleft : _get("#mm-tabcloseleft"),
            exit : _get("#mm-exit")
        };

        function _get(id) {
            return $(id, ui.tabMenu);
        }

        // 刷新
        tabMenuUI.tabupdate.click(function() {
            var currTab = ui.main.tabs('getSelected');
            fnUpdateTab(currTab, {
                url : $(currTab.panel('options').content).attr('src')
            });
        });
        // 关闭当前
        tabMenuUI.tabclose.click(function() {
            ui.main.tabs('close', _getCurrentId());
        });
        // 全部关闭
        tabMenuUI.tabcloseall.click(function() {
            var _tabs = ui.main.tabs("tabs");
            for (var i = _tabs.length; i > -1; i--) {
                ui.main.tabs("close", i);
            }
        });
        // 关闭除当前之外的TAB
        tabMenuUI.tabcloseother.click(function() {
            var _tabs = ui.main.tabs("tabs");
            var index = _getCurrentId();
            for (var i = _tabs.length; i > -1; i--) {
                if (index < i) {
                    ui.main.tabs("close", i);
                } else if (index > i) {
                    ui.main.tabs("close", i);
                    index--;
                }
                if (i == 0) {
                    ui.main.tabs("select", 1);
                }
            }
        });
        // 关闭当前右侧的TAB
        tabMenuUI.tabcloseright.click(function() {
            return _toCloseTab(function(i) {
                return _getCurrentId() < i;
            });
        });
        // 关闭当前左侧的TAB
        tabMenuUI.tabcloseleft.click(function() {
            return _toCloseTab(function(i) {
                return _getCurrentId() > i;
            });
        });

        // 退出
        tabMenuUI.exit.click(function() {
            ui.tabMenu.menu('hide');
        });

        function _getCurrentId() {
            var tab = ui.main.tabs('getSelected');
            return ui.main.tabs('getTabIndex', tab);
        }

        function _toCloseTab(affirm) {
            var _tabs = ui.main.tabs("tabs");
            for (var i = _tabs.length; i > -1; i--) {
                if (affirm(i)) {
                    ui.main.tabs("close", i);
                }
            }
            return false;
        }
    }

    function _closeAllTab() {
        var _tabs = ui.main.tabs("tabs");
        for (var i = _tabs.length; i > -1; i--) {
            ui.main.tabs("close", i);
        }
    }

    function _initTabs() {
        // 第一页不给关闭
        ui.main.tabs({
            onBeforeClose : function(title, index) {
                if (0 === index)
                    return false;
            }
        });
        _initTabRightClickMenu();
        _initTabRightClick();
    }
}