$(document).ready(function () {

    var testTable = $('#mydataTable').DataTable({
        "scrollY": "400px",
        "scrollCollapse": true,
        "paging": false,
        "columnDefs": [{
            "targets": 2,
            "searchable": false,
            "orderable": false,
        }, {
            "targets": 3,
            "searchable": false,
            "orderable": false,
        }, {
            "targets": 4,
            "searchable": false,
            "orderable": false,
        }],
    });

    bindEvenCheckAllCheckBox('checkAll', testTable);
    enableOrDisableDeleteAll();
    autoCheckCheckboxAll('mydataTable', 'checkAll');
})

function autoCheckCheckboxAll(table, id) {
    var totalCheckbox = $('#' + table).find('tbody input[type=checkbox]').length;
    $('#' + table).find('tbody input[type=checkbox]').each(function () {
        $(this).on('change', function () {
            var totalCheckboxChecked = $('#' + table).find('tbody input[type=checkbox]:checked').length;
            if (totalCheckboxChecked == totalCheckbox) {
                $('#' + id).prop('checked', true);
            } else {
                $('#' + id).prop('checked', false);
            }
        })
    })
}

function bindEvenCheckAllCheckBox(id, table) {
    $('#' + id).click(function () {
        var cells = table.column(3).nodes(), state = this.checked;
        for (var i = 0; i < cells.length; i += 1) {
            cells[i].querySelector('input[type=checkbox]').checked = state;
        }
    })
};

function enableOrDisableDeleteAll() {
    $('input[type=checkbox]').click(function () {
        if ($(this).attr('id') != 'checkAll' && $(this).prop('checked') == false && $('#checkAll').prop('checked') == true) {
            $('#checkAll').prop('checked',false);
        }
        if ($('input[type=checkbox]:checked').length > 0) {
            $('#deleteAll').prop('disabled', false);
        } else {
            $('#deleteAll').prop('disabled', true);
        }
    })
}