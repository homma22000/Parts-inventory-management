<template>
  <h1>在庫管理</h1>
  <section id="itemPage">
    <form id="itemRegisterForm" >
      <label for="itemCode">コード</label>
      <input id="itemCodeField" type="text" >
      <label for="itemName">名称</label>
      <input id="itemNameField" type="text" readonly required>
      <label for="itemSection">区分</label>
      <select id="itemSectionField" required>
        <option value="RECEIPT">入庫</option>
        <option value="ISSUE">出庫</option>
      </select>
      <label for="itemQuantity">数量</label>
      <input id="itemQuantityField" type="number" required>
      <button>登録</button>
    </form>
    <hr>
    <div>
      <table>
        <thead id="itemListHeader">
        <tr>
          <th>コード</th>
          <th>名称</th>
          <th>在庫数量</th>
        </tr>
        </thead>
        <tbody id="itemList">
        <tr>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>

<style scoped>
.suggestions {
  border: 1px solid #ccc;
  max-height: 150px;
  overflow-y: auto;
  background-color: white;
  position: absolute;
  width: calc(100% - 22px); /* Adjust width to match the input field */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  z-index: 1000;
  margin-top: 5px;
  padding: 0;
  list-style-type: none;
}

.suggestions li {
  padding: 10px;
  cursor: pointer;
}

.suggestions li:hover {
  background-color: #f0f0f0;
}

.suggestions li:nth-child(even) {
  background-color: #fafafa;
}

.suggestions li:nth-child(odd) {
  background-color: #fff;
}

#itemNameField {
  width: 30%;
  box-sizing: border-box;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>