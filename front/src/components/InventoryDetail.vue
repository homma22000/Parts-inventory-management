<template>
  <body>

  <h1>棚卸</h1>

  <form id="inventoryRegisterForm">
    <button>登録</button>
    <hr />
    <div>
      <table>
        <thead>
        <tr>
          <th>コード</th>
          <th>名称</th>
          <th>論理在庫数量</th>
          <th>実在庫数量</th>
          <th>差分</th>
          <th>備考</th>
        </tr>
        </thead>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td><input type="number" name="actualStock" min="0"></td>
          <td></td>
          <td><input type="text" name="remarks" required></td>
        </tr>
      </table>
    </div>
  </form>
  </body>
</template>