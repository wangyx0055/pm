/*!
 * FileInput Spanish (Latin American) Translations
 *
 * This file must be loaded after 'fileinput.js'. Patterns in braces '{}', or
 * any HTML markup tags in the messages must not be converted or translated.
 *
 * @see http://github.com/kartik-v/bootstrap-fileinput
 *
 * NOTE: this file must be saved in UTF-8 encoding.
 */
(function ($) {
    "use strict";
    $.fn.fileinput.locales.es = {
    		fileSingle: '单个文件',
            filePlural: '多个文件',
            browseLabel: '选择文件 &hellip;',
            removeLabel: '删除文件',
            removeTitle: '删除选中文件',
            cancelLabel: '取消',
            cancelTitle: '取消上传',
            uploadLabel: '上传',
            uploadTitle: '上传选中文件',
            msgSizeTooLarge: '文件 "{name}" (<b>{size} KB</b>) 超出最大上传限额 <b>{maxSize} KB</b>. 请重新上传!',
            msgFilesTooLess: '文件数量必须大于 <b>{n}</b> {files} ，请重新上传！',
            msgFilesTooMany: '上传的文件数量 <b>({n})</b> 超出了一次最大上传数量 <b>{m}</b>. 请重新上传!',
            msgFileNotFound: '文件 "{name}" 未找到!',
            msgFileSecured: 'Security restrictions prevent reading the file "{name}".',
            msgFileNotReadable: '文件 "{name}" 不可读.',
            msgFilePreviewAborted: '文件预览中止： "{name}".',
            msgFilePreviewError: '读文件时发生了一个错误 "{name}".',
            msgInvalidFileType: 'Invalid type for file "{name}". Only "{types}" files are supported.',
            msgInvalidFileExtension: 'Invalid extension for file "{name}". Only "{extensions}" files are supported.',
            msgValidationError: '文件上传失败',
            msgLoading: '正在加载 {index} 个 {files} &hellip;',
            msgProgress: '正在加载 {index} 个 {files} - {name} - 已完成 {percent}%.',
            msgSelected: '选中{n}个文件',
            msgFoldersNotAllowed: '仅允许拖入和删除文件! 不支持文件夹.',
            dropZoneTitle: '请拖入或选择文件 &hellip;'
    };

    $.extend($.fn.fileinput.defaults, $.fn.fileinput.locales.es);
})(window.jQuery);
