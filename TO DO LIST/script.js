const input = document.getElementById("todo-input");
const addBtn = document.getElementById("btn");
const list = document.getElementById("todo-list");

const saved = localStorage.getItem("todos");
const todos = saved ? JSON.parse(saved) : [];

function saveTodos() {
  localStorage.setItem("todos", JSON.stringify(todos));
}

function createNode(todo, index) {
  const li = document.createElement("li");
  const checkbox = document.createElement("input");
  checkbox.type = "checkbox";
  checkbox.checked = !!todo.completed;
  checkbox.addEventListener("change", () => {
    todo.completed = checkbox.checked;
    textspan.style.textDecoration = todo.completed ? "line-through" : "";
    saveTodos();
  });

  const textspan = document.createElement("span");
  textspan.textContent = todo.text;
  textspan.style.margin = "0 8px";
  if (todo.completed) {
    textspan.style.textDecoration = "line-through";
  }

  textspan.addEventListener("dblclick", () => {
    const newText = prompt("Edit Todo", todo.text);
    if (newText !== null) {
      todo.text = newText.trim();
      textspan.textContent = todo.text;
      saveTodos();
    }
  });

  const delBtn = document.createElement("button");
  delBtn.textContent = "Delete";
  delBtn.addEventListener("click", () => {
    todos.splice(index, 1);
    render();
    saveTodos();
  });

  li.appendChild(checkbox);
  li.appendChild(textspan);
  li.appendChild(delBtn);

  return li;
}

function render() {
  list.innerHTML = "";
  todos.forEach((todo, index) => {
    const node = createNode(todo, index);
    list.appendChild(node);
  });
}

function addTodo() {
  const text = input.value.trim();
  if (!text) {
    return;
  }
  todos.push({ text: text, completed: false });
  input.value = "";
  render();
  saveTodos();
}

addBtn.addEventListener("click", addTodo);
input.addEventListener("keydown", (e) => {
  if (e.key == "Enter") {
    addTodo();
  }
});
render();
