<template>
  <header>
    <h1>部品在庫管理システム</h1>
  </header>
  <nav id="mainMenu">
    <RouterLink to="/pages/inventories">在庫管理</RouterLink>
    <RouterLink to="/pages/parts">部品管理</RouterLink>
    <RouterLink to="/pages/stocktaking">棚卸</RouterLink>
  </nav>
  <dialog id="messageDialog">
  </dialog>
</template>
