use rlua::{Function, Lua, MetaMethod, Result, UserData, UserDataMethods, Variadic};
use serde::{Serialize, Deserialize};

#[derive(Debug, Serialize, Deserialize)]
struct RunData {
    code: String,
}

fn main() {
    let lua = Lua::new();

    let data = RunData {
        code: "return 1 + 2".to_string(),
    };

    let x = lua.context(|lua_context| {
       lua_context.load(&data.code).eval::<i32>().unwrap()
    });

    println!("{}", x);
}
