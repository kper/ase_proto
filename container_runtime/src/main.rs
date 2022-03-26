use actix_web::{get, post, web, App, HttpResponse, HttpServer, Responder};
use rlua::{Function, Lua, MetaMethod, Result, UserData, UserDataMethods, Variadic};
use serde::{Serialize, Deserialize};

/*
#[actix_web::main]
async fn main() -> std::io::Result<()> {
    HttpServer::new(|| {
        App::new()
            .route("/run", web::post().to(run))
    })
    .bind(("127.0.0.1", 8082))?
    .run()
    .await
}*/

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

    //return HttpResponse::Ok().body(format!("{}", x));
}