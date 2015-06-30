$(document).ready(
        function() {
            var app = new CCApp();
            /** 构建案件页面的标签数据 */
            app.aipcase = {};
            app.aipcase.insertTextAreaData = fnInsertTextAreaData;

            /**
             * 将 myValue插入myField的textArea对象中
             */
            function fnInsertTextAreaData(myField, myValue) {
                // IE support
                if (document.selection) {
                    myField.focus();
                    sel = document.selection.createRange();
                    sel.text = myValue;
                    sel.select();
                } else if (myField.selectionStart || myField.selectionStart == '0') {
                    // MOZILLA/NETSCAPE support
                    var startPos = myField.selectionStart;
                    var endPos = myField.selectionEnd;
                    // save scrollTop before insert www.keleyi.com
                    var restoreTop = myField.scrollTop;
                    myField.value = myField.value.substring(0, startPos) + myValue
                            + myField.value.substring(endPos, myField.value.length);
                    if (restoreTop > 0) {
                        myField.scrollTop = restoreTop;
                    }
                    myField.focus();
                    myField.selectionStart = startPos + myValue.length;
                    myField.selectionEnd = startPos + myValue.length;
                } else {
                    myField.value += myValue;
                    myField.focus();
                }
            }
        });