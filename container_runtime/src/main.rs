use rlua::{Function, Lua, MetaMethod, Result, UserData, UserDataMethods, Variadic};
use serde::{Serialize, Deserialize};

#[derive(Debug, Serialize, Deserialize)]
struct RunData {
    code: String,
}

fn main() {
    let mut lua = Lua::new();

    let data = RunData {
        code: r#"return stdlib(1)"#.to_string(),
    };

    setup_game_library(&mut lua);

    let x = lua.context(|lua_ctx| {
        lua_ctx.load(&data.code).eval::<bool>().unwrap()
    });

    println!("{}", x);
}

/// Setup functions which the user can use directly in the lua code.
fn setup_game_library(lua: &mut Lua) {

    lua.context(|lua_ctx| {
        let globals = lua_ctx.globals();
        let check_equal = lua_ctx.create_function(|_, (list1, list2): (Vec<String>, Vec<String>)| {
            // This function just checks whether two string lists are equal, and in an inefficient way.
            // Lua callbacks return `rlua::Result`, an Ok value is a normal return, and an Err return
            // turns into a Lua 'error'.  Again, any type that is convertible to Lua may be returned.
            Ok(list1 == list2)
        }).unwrap();

        globals.set("check_equal", check_equal);

        // ---

        let check = lua_ctx.create_function(|_, (a, b): (usize, usize)| {
            Ok(a == b)
        }).unwrap();

        globals.set("check", check);

        // ---

        let can_I_use_stdlib =  lua_ctx.create_function(|_, (a): (usize)| {
            let mut h = std::collections::HashSet::new();
            h.insert(1);
            h.insert(2);
            h.insert(3);
            Ok(h.contains(a))
        }).unwrap();

        globals.set("stdlib", can_I_use_stdlib);
    });
}