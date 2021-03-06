PGDMP             
            w            AlmacenDeRopa_3    10.4    10.4 �    R           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            S           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            T           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            U           1262    27633    AlmacenDeRopa_3    DATABASE     �   CREATE DATABASE "AlmacenDeRopa_3" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';
 !   DROP DATABASE "AlmacenDeRopa_3";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            V           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            W           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    28248    movimientos_bancarios    TABLE     �  CREATE TABLE public.movimientos_bancarios (
    id_movimientos integer NOT NULL,
    codigo character varying(50),
    idcuenta integer NOT NULL,
    fecha date NOT NULL,
    descripcion character varying(50),
    deposito double precision,
    retiro double precision,
    saldo double precision NOT NULL,
    CONSTRAINT movimientos_bancarios_deposito_check CHECK ((deposito >= (0)::double precision)),
    CONSTRAINT movimientos_bancarios_retiro_check CHECK ((retiro <= (0)::double precision))
);
 )   DROP TABLE public.movimientos_bancarios;
       public         postgres    false    3                       1255    28261 '   calcular_movimientos_de_cuenta(integer)    FUNCTION     �   CREATE FUNCTION public.calcular_movimientos_de_cuenta(id_cuenta integer) RETURNS SETOF public.movimientos_bancarios
    LANGUAGE plpgsql
    AS $$


begin

return query select * from movimientos_bancarios where idcuenta = id_cuenta;


end

$$;
 H   DROP FUNCTION public.calcular_movimientos_de_cuenta(id_cuenta integer);
       public       postgres    false    1    3    242                       1255    28185 !   calcular_saldo_de_cuenta(integer)    FUNCTION     y  CREATE FUNCTION public.calcular_saldo_de_cuenta(id_cuenta integer) RETURNS double precision
    LANGUAGE plpgsql
    AS $$declare

valueA double precision;
valueB double precision;
valueRes double precision;
begin

valueA := (select sum(depositos.total) from depositos 
where depositos.idcuenta = id_cuenta);
valueB := (select sum(retiros.valor) from retiros
where retiros.idcuenta = id_cuenta);

valueRes := valueA + valueB;
if(valueRes is null) then
	if(valueA is null and valueB is null) then
	valueRes := 0;
	elseif(valueA is null)then
	valueRes := valueB;
	else 
	valueRes := valueA;
	end if;

end if;
return valueRes;
end

$$;
 B   DROP FUNCTION public.calcular_saldo_de_cuenta(id_cuenta integer);
       public       postgres    false    3    1                       1255    28270 *   generar_codigo(character varying, integer)    FUNCTION     L  CREATE FUNCTION public.generar_codigo(tipo character varying, id_cuenta integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $$

declare

valueA varchar;
valueB varchar;

begin

if(tipo = 'DEPOSITO') then
valueA := concat('D-',(select count(codigo) from movimientos_bancarios where idcuenta = id_cuenta and codigo like 'D_%')+1);
elseif(tipo = 'RETIRO') then
valueA := concat('R-',(select count(codigo) from movimientos_bancarios where idcuenta = id_cuenta and codigo like 'R_%')+1);
else
raise exception 'No ha especificado RETIRO o DEPOSITO';
end if;

return valueA;
end

$$;
 P   DROP FUNCTION public.generar_codigo(tipo character varying, id_cuenta integer);
       public       postgres    false    3    1                       1255    28295 t   ingresar_deposito(integer, character varying, date, double precision, integer, character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.ingresar_deposito(id_cuenta_n integer, n_deposito_n character varying, fecha_n date, efectivo_n double precision, idcheque_n integer, depositante_n character varying, descripcion_n character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$declare

valueA double precision;
depositante_n1 varchar;
descripcion_n1 varchar;

begin

depositante_n1 := upper(depositante_n);
descripcion_n1 := upper(descripcion_n);

if(idcheque_n <> 0 and efectivo_n <> 0) then

valueA := (select valor from cheques where idcheque = idcheque_n);
insert into depositos (idcuenta,n_deposito,fecha,efectivo,cheque,depositante,descripcion,total)
			   values (id_cuenta_n ,n_deposito_n,fecha_n ,efectivo_n  ,idcheque_n ,depositante_n1 ,descripcion_n1, (valueA+efectivo_n) );

update cheques set descripcion = 'DEPOSITADO' WHERE idcheque = idcheque_n;

elseif(idcheque_n = 0 AND efectivo_n <> 0)then
insert into depositos (idcuenta,n_deposito,fecha,efectivo,depositante,descripcion,total)
			   values (id_cuenta_n ,n_deposito_n,fecha_n ,efectivo_n ,depositante_n1 ,descripcion_n1, efectivo_n );
else
	valueA := (select valor from cheques where idcheque = idcheque_n);		
			 insert into depositos (idcuenta,n_deposito,fecha,cheque,depositante,descripcion,total)
			   values (id_cuenta_n ,n_deposito_n,fecha_n  ,idcheque_n ,depositante_n1 ,descripcion_n1, valueA );
update cheques set descripcion = 'DEPOSITADO' WHERE idcheque = idcheque_n;

end if;


return;
end

$$;
 �   DROP FUNCTION public.ingresar_deposito(id_cuenta_n integer, n_deposito_n character varying, fecha_n date, efectivo_n double precision, idcheque_n integer, depositante_n character varying, descripcion_n character varying);
       public       postgres    false    1    3                       1255    27634    sp_actualizarcaja()    FUNCTION     �  CREATE FUNCTION public.sp_actualizarcaja() RETURNS trigger
    LANGUAGE plpgsql
    AS $$begin
if(new.credito=true)then
	insert into cuenta_por_cobrar(n_factura,valorpendiente,descripcion,estado) 
	values(new.numerofactura,new.total,'POR VENTA A CREDITO','PENDIENTE');
else
	insert into caja(pk_ventafactura_fk,fecha,hora,valor,referencia)
	 values (new.numerofactura,new.fecha,now(),new.total,'VENTA');
end if;

return new;
end
$$;
 *   DROP FUNCTION public.sp_actualizarcaja();
       public       postgres    false    3    1            �            1255    27635    sp_actualizarcuentascobradas()    FUNCTION       CREATE FUNCTION public.sp_actualizarcuentascobradas() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE


declare
tipox double precision;
begin
update cuenta_por_cobrar 
	set valorpendiente= cuenta_por_cobrar.valorpendiente - new.valor
						 where cuenta_por_cobrar.idcuenta_por_cobrar=new.pk_cuentas_por_cobrar_fk;
insert into caja(fecha,hora,valor,referencia) values(now(),now(),new.valor,
concat('POR PAGO DE LA CUENTA',' ',new.pk_cuentas_por_cobrar_fk,' ','EN CXP') );
if((select cuenta_por_cobrar.valorpendiente from cuenta_por_cobrar
   where idcuenta_por_cobrar = new.pk_cuentas_por_cobrar_fk) <=0 )then
	update cuenta_por_cobrar 
	set estado='PAGADO' where cuenta_por_cobrar.idcuenta_por_cobrar=new.pk_cuentas_por_cobrar_fk;
end if;

return new;
end

$$;
 5   DROP FUNCTION public.sp_actualizarcuentascobradas();
       public       postgres    false    3    1                       1255    28403 (   sp_actualizarcuentasporcobrarconcheque()    FUNCTION     >  CREATE FUNCTION public.sp_actualizarcuentasporcobrarconcheque() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE

begin
update cuenta_por_cobrar 
	set valorpendiente= cuenta_por_cobrar.valorpendiente - new.valor
						 where cuenta_por_cobrar.idcuenta_por_cobrar=new.pk_cuentaxcobrar_fk;

if((select cuenta_por_cobrar.valorpendiente from cuenta_por_cobrar
   where idcuenta_por_cobrar = new.pk_cuentaxcobrar_fk) <=0 )then
	update cuenta_por_cobrar 
	set estado='PAGADO' where cuenta_por_cobrar.idcuenta_por_cobrar=new.pk_cuentaxcobrar_fk;
end if;
return new;
end

$$;
 ?   DROP FUNCTION public.sp_actualizarcuentasporcobrarconcheque();
       public       postgres    false    1    3                       1255    27636    sp_actualizarstockdevclientes()    FUNCTION     �   CREATE FUNCTION public.sp_actualizarstockdevclientes() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
update bodega 
	set stock=bodega.stock + new.cantidad
						 where idproducto=new.idproducto;

return new;
end
$$;
 6   DROP FUNCTION public.sp_actualizarstockdevclientes();
       public       postgres    false    3    1            �            1255    27637    sp_actualizarstockdevsoc()    FUNCTION     �   CREATE FUNCTION public.sp_actualizarstockdevsoc() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
update bodega 
	set stock=bodega.stock - new.cantidad
						 where idproducto=new.idproducto;

return new;
end
$$;
 1   DROP FUNCTION public.sp_actualizarstockdevsoc();
       public       postgres    false    3    1            �            1255    27638    sp_actualizarstockfact()    FUNCTION     �   CREATE FUNCTION public.sp_actualizarstockfact() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
update bodega 
	set stock=bodega.stock - new.cantidad
						 where idproducto=new.idproducto;

return new;
end
$$;
 /   DROP FUNCTION public.sp_actualizarstockfact();
       public       postgres    false    1    3                       1255    27639    sp_actualizarstocksocios()    FUNCTION     �   CREATE FUNCTION public.sp_actualizarstocksocios() RETURNS trigger
    LANGUAGE plpgsql
    AS $$begin
update bodega 
	set stock=bodega.stock + new.cantidad, costo = new.costo
						 where idproducto=new.idproducto;

return new;
end
$$;
 1   DROP FUNCTION public.sp_actualizarstocksocios();
       public       postgres    false    1    3                       1255    28268    sp_insert_trans_depo()    FUNCTION     �  CREATE FUNCTION public.sp_insert_trans_depo() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE

x DOUBLE PRECISION;

BEGIN

x := (calcular_saldo_de_cuenta(new.idcuenta));
	
	insert into movimientos_bancarios (codigo,idcuenta,fecha,descripcion,deposito,retiro,saldo)
	values ((generar_codigo('DEPOSITO',NEW.idcuenta)),new.idcuenta,now(),(concat('DEPOSITO - ',new.descripcion)),
											new.total,0,((x)));

	
	return new;
END;
$$;
 -   DROP FUNCTION public.sp_insert_trans_depo();
       public       postgres    false    1    3                       1255    28281    sp_insert_trans_retiro()    FUNCTION     �  CREATE FUNCTION public.sp_insert_trans_retiro() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE

x DOUBLE PRECISION;

BEGIN

x := (calcular_saldo_de_cuenta(new.idcuenta));
	
	insert into movimientos_bancarios (codigo,idcuenta,fecha,descripcion,deposito,retiro,saldo)
	values ((generar_codigo('RETIRO',NEW.idcuenta)),new.idcuenta,now(),(concat('RETIRO - ',new.descripcion)),
											0,new.valor,((x)));

	
	return new;
END;

$$;
 /   DROP FUNCTION public.sp_insert_trans_retiro();
       public       postgres    false    1    3            �            1259    38696    AAA    TABLE     1   CREATE TABLE public."AAA" (
    "DSA" integer
);
    DROP TABLE public."AAA";
       public         postgres    false    3            �            1259    27640    abonos    TABLE     �   CREATE TABLE public.abonos (
    idabonos integer NOT NULL,
    pk_cuentas_por_cobrar_fk integer NOT NULL,
    valor double precision NOT NULL,
    descripcion character varying
);
    DROP TABLE public.abonos;
       public         postgres    false    3            �            1259    27646    abonos_idabonos_seq    SEQUENCE     �   CREATE SEQUENCE public.abonos_idabonos_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.abonos_idabonos_seq;
       public       postgres    false    196    3            X           0    0    abonos_idabonos_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.abonos_idabonos_seq OWNED BY public.abonos.idabonos;
            public       postgres    false    197            �            1259    27648    bancos    TABLE     �   CREATE TABLE public.bancos (
    idbanco integer NOT NULL,
    nombre character varying NOT NULL,
    descripcion character varying
);
    DROP TABLE public.bancos;
       public         postgres    false    3            �            1259    27654    bancos_idbanco_seq    SEQUENCE     �   CREATE SEQUENCE public.bancos_idbanco_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.bancos_idbanco_seq;
       public       postgres    false    3    198            Y           0    0    bancos_idbanco_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.bancos_idbanco_seq OWNED BY public.bancos.idbanco;
            public       postgres    false    199            �            1259    27656    bodega    TABLE     �   CREATE TABLE public.bodega (
    idproducto integer NOT NULL,
    idsocio integer,
    stock integer,
    costo double precision
);
    DROP TABLE public.bodega;
       public         postgres    false    3            �            1259    27659    bodega_idproducto_seq    SEQUENCE     �   CREATE SEQUENCE public.bodega_idproducto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.bodega_idproducto_seq;
       public       postgres    false    200    3            Z           0    0    bodega_idproducto_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.bodega_idproducto_seq OWNED BY public.bodega.idproducto;
            public       postgres    false    201            �            1259    27661    caja    TABLE     �   CREATE TABLE public.caja (
    idcaja integer NOT NULL,
    pk_ventafactura_fk character varying,
    fecha date NOT NULL,
    hora time without time zone NOT NULL,
    valor double precision NOT NULL,
    referencia character varying
);
    DROP TABLE public.caja;
       public         postgres    false    3            �            1259    27667    caja_idcaja_seq    SEQUENCE     �   CREATE SEQUENCE public.caja_idcaja_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.caja_idcaja_seq;
       public       postgres    false    3    202            [           0    0    caja_idcaja_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.caja_idcaja_seq OWNED BY public.caja.idcaja;
            public       postgres    false    203            �            1259    27669 	   categoria    TABLE     n   CREATE TABLE public.categoria (
    idcategoria integer NOT NULL,
    categoria character varying NOT NULL
);
    DROP TABLE public.categoria;
       public         postgres    false    3            �            1259    27675    categoria_idcategoria_seq    SEQUENCE     �   CREATE SEQUENCE public.categoria_idcategoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.categoria_idcategoria_seq;
       public       postgres    false    204    3            \           0    0    categoria_idcategoria_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.categoria_idcategoria_seq OWNED BY public.categoria.idcategoria;
            public       postgres    false    205            �            1259    27677    cheques    TABLE     B  CREATE TABLE public.cheques (
    idcheque integer NOT NULL,
    pk_cuentaxcobrar_fk integer,
    n_cheque integer,
    cheque_banco character varying,
    titular_cuenta character varying,
    fecha_ingreso date,
    fecha_deposito date NOT NULL,
    valor double precision NOT NULL,
    descripcion character varying
);
    DROP TABLE public.cheques;
       public         postgres    false    3            �            1259    27683    cheques_idcheque_seq    SEQUENCE     �   CREATE SEQUENCE public.cheques_idcheque_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.cheques_idcheque_seq;
       public       postgres    false    3    206            ]           0    0    cheques_idcheque_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.cheques_idcheque_seq OWNED BY public.cheques.idcheque;
            public       postgres    false    207            �            1259    27685    cliente    TABLE     �   CREATE TABLE public.cliente (
    cedula character varying NOT NULL,
    nombre character varying NOT NULL,
    apellido character varying NOT NULL,
    direccion character varying,
    telefono character varying
);
    DROP TABLE public.cliente;
       public         postgres    false    3            �            1259    28109    cuenta    TABLE     �   CREATE TABLE public.cuenta (
    idcuenta integer NOT NULL,
    idbanco integer NOT NULL,
    tipo character varying(20) NOT NULL,
    ncuenta character varying(25) NOT NULL,
    nombre character varying(50) NOT NULL
);
    DROP TABLE public.cuenta;
       public         postgres    false    3            �            1259    28107    cuenta_idcuenta_seq    SEQUENCE     �   CREATE SEQUENCE public.cuenta_idcuenta_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.cuenta_idcuenta_seq;
       public       postgres    false    3    239            ^           0    0    cuenta_idcuenta_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.cuenta_idcuenta_seq OWNED BY public.cuenta.idcuenta;
            public       postgres    false    238            �            1259    27691    cuenta_por_cobrar    TABLE     �  CREATE TABLE public.cuenta_por_cobrar (
    idcuenta_por_cobrar integer NOT NULL,
    n_factura character varying NOT NULL,
    valorpendiente double precision NOT NULL,
    descripcion character varying,
    estado character varying NOT NULL,
    CONSTRAINT estado_cxc_check CHECK (((estado)::text = ANY (ARRAY[('PENDIENTE'::character varying)::text, ('PAGADO'::character varying)::text])))
);
 %   DROP TABLE public.cuenta_por_cobrar;
       public         postgres    false    3            �            1259    27698 )   cuenta_por_cobrar_idcuenta_por_cobrar_seq    SEQUENCE     �   CREATE SEQUENCE public.cuenta_por_cobrar_idcuenta_por_cobrar_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 @   DROP SEQUENCE public.cuenta_por_cobrar_idcuenta_por_cobrar_seq;
       public       postgres    false    209    3            _           0    0 )   cuenta_por_cobrar_idcuenta_por_cobrar_seq    SEQUENCE OWNED BY     w   ALTER SEQUENCE public.cuenta_por_cobrar_idcuenta_por_cobrar_seq OWNED BY public.cuenta_por_cobrar.idcuenta_por_cobrar;
            public       postgres    false    210            �            1259    27708    cuentas_por_pagar    TABLE     l  CREATE TABLE public.cuentas_por_pagar (
    idcuentaxpagar integer NOT NULL,
    fecha date NOT NULL,
    valor double precision NOT NULL,
    descripcion character varying,
    estado character varying NOT NULL,
    CONSTRAINT estado_cxp_check CHECK (((estado)::text = ANY (ARRAY[('PAGADO'::character varying)::text, ('PENDIENTE'::character varying)::text])))
);
 %   DROP TABLE public.cuentas_por_pagar;
       public         postgres    false    3            �            1259    27715 $   cuentas_por_pagar_idcuentaxpagar_seq    SEQUENCE     �   CREATE SEQUENCE public.cuentas_por_pagar_idcuentaxpagar_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.cuentas_por_pagar_idcuentaxpagar_seq;
       public       postgres    false    3    211            `           0    0 $   cuentas_por_pagar_idcuentaxpagar_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE public.cuentas_por_pagar_idcuentaxpagar_seq OWNED BY public.cuentas_por_pagar.idcuentaxpagar;
            public       postgres    false    212            �            1259    27717 	   depositos    TABLE     |  CREATE TABLE public.depositos (
    iddeposito integer NOT NULL,
    idcuenta integer NOT NULL,
    n_deposito character varying,
    fecha date NOT NULL,
    efectivo double precision,
    cheque integer,
    depositante character varying,
    descripcion character varying,
    total double precision NOT NULL,
    CONSTRAINT total_ck CHECK ((total > (0)::double precision))
);
    DROP TABLE public.depositos;
       public         postgres    false    3            �            1259    27724    depositos_iddeposito_seq    SEQUENCE     �   CREATE SEQUENCE public.depositos_iddeposito_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.depositos_iddeposito_seq;
       public       postgres    false    3    213            a           0    0    depositos_iddeposito_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.depositos_iddeposito_seq OWNED BY public.depositos.iddeposito;
            public       postgres    false    214            �            1259    27726    detallefactura    TABLE     �   CREATE TABLE public.detallefactura (
    numerofactura character varying NOT NULL,
    idproducto integer NOT NULL,
    cantidad integer NOT NULL,
    preciounitario double precision NOT NULL,
    preciototal double precision NOT NULL
);
 "   DROP TABLE public.detallefactura;
       public         postgres    false    3            �            1259    27732    devolucion_de_cliente    TABLE     �   CREATE TABLE public.devolucion_de_cliente (
    numerofactura character varying NOT NULL,
    idproducto integer NOT NULL,
    cantidad integer NOT NULL,
    descripcion character varying NOT NULL,
    fecha date NOT NULL
);
 )   DROP TABLE public.devolucion_de_cliente;
       public         postgres    false    3            �            1259    27738    devolucionesasocios    TABLE     �   CREATE TABLE public.devolucionesasocios (
    idsocio integer NOT NULL,
    idproducto integer NOT NULL,
    fecha date NOT NULL,
    cantidad integer NOT NULL,
    descripcion character varying NOT NULL
);
 '   DROP TABLE public.devolucionesasocios;
       public         postgres    false    3            �            1259    27744    factura    TABLE       CREATE TABLE public.factura (
    numerofactura character varying NOT NULL,
    cliente_cedula character varying NOT NULL,
    fecha date NOT NULL,
    subtotal double precision NOT NULL,
    iva double precision,
    total double precision NOT NULL,
    credito boolean
);
    DROP TABLE public.factura;
       public         postgres    false    3            �            1259    28246 (   movimientos_bancarios_id_movimientos_seq    SEQUENCE     �   CREATE SEQUENCE public.movimientos_bancarios_id_movimientos_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ?   DROP SEQUENCE public.movimientos_bancarios_id_movimientos_seq;
       public       postgres    false    242    3            b           0    0 (   movimientos_bancarios_id_movimientos_seq    SEQUENCE OWNED BY     u   ALTER SEQUENCE public.movimientos_bancarios_id_movimientos_seq OWNED BY public.movimientos_bancarios.id_movimientos;
            public       postgres    false    241            �            1259    27750    notaentrega    TABLE     �   CREATE TABLE public.notaentrega (
    idnotadeeentrega integer NOT NULL,
    idsocio integer NOT NULL,
    numero_nota_entrega character varying,
    fecha date NOT NULL,
    receptor character varying
);
    DROP TABLE public.notaentrega;
       public         postgres    false    3            �            1259    27756    notaentrega_bodega    TABLE     �   CREATE TABLE public.notaentrega_bodega (
    idnotaentrega integer NOT NULL,
    idproducto integer NOT NULL,
    cantidad integer NOT NULL,
    costo double precision
);
 &   DROP TABLE public.notaentrega_bodega;
       public         postgres    false    3            �            1259    27759     notaentrega_idnotadeeentrega_seq    SEQUENCE     �   CREATE SEQUENCE public.notaentrega_idnotadeeentrega_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.notaentrega_idnotadeeentrega_seq;
       public       postgres    false    3    219            c           0    0     notaentrega_idnotadeeentrega_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.notaentrega_idnotadeeentrega_seq OWNED BY public.notaentrega.idnotadeeentrega;
            public       postgres    false    221            �            1259    27761 	   productos    TABLE     �   CREATE TABLE public.productos (
    idproducto integer NOT NULL,
    categoria integer,
    nombre character varying NOT NULL,
    detalle character varying
);
    DROP TABLE public.productos;
       public         postgres    false    3            �            1259    27767    productos_idproducto_seq    SEQUENCE     �   CREATE SEQUENCE public.productos_idproducto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.productos_idproducto_seq;
       public       postgres    false    3    222            d           0    0    productos_idproducto_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.productos_idproducto_seq OWNED BY public.productos.idproducto;
            public       postgres    false    223            �            1259    27769    retiros    TABLE       CREATE TABLE public.retiros (
    idretiros integer NOT NULL,
    idcuenta integer NOT NULL,
    fecha date NOT NULL,
    descripcion character varying,
    valor double precision NOT NULL,
    CONSTRAINT valor_ck CHECK ((valor < (0)::double precision))
);
    DROP TABLE public.retiros;
       public         postgres    false    3            �            1259    27776    retiros_idretiros_seq    SEQUENCE     �   CREATE SEQUENCE public.retiros_idretiros_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.retiros_idretiros_seq;
       public       postgres    false    3    224            e           0    0    retiros_idretiros_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.retiros_idretiros_seq OWNED BY public.retiros.idretiros;
            public       postgres    false    225            �            1259    27778    socios    TABLE     �   CREATE TABLE public.socios (
    idsocio integer NOT NULL,
    ruc character varying,
    nombre character varying NOT NULL,
    referencia character varying
);
    DROP TABLE public.socios;
       public         postgres    false    3            �            1259    27784    socios_idsocio_seq    SEQUENCE     �   CREATE SEQUENCE public.socios_idsocio_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.socios_idsocio_seq;
       public       postgres    false    3    226            f           0    0    socios_idsocio_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.socios_idsocio_seq OWNED BY public.socios.idsocio;
            public       postgres    false    227            �            1259    27786 	   telefonos    TABLE     �   CREATE TABLE public.telefonos (
    idsocio integer NOT NULL,
    telefono character varying NOT NULL,
    referencia character varying
);
    DROP TABLE public.telefonos;
       public         postgres    false    3            �            1259    28178 
   total_caja    VIEW     \   CREATE VIEW public.total_caja AS
 SELECT sum(caja.valor) AS total_caja
   FROM public.caja;
    DROP VIEW public.total_caja;
       public       postgres    false    202    3            �            1259    27792    usuario    TABLE     �   CREATE TABLE public.usuario (
    idusuario integer NOT NULL,
    nombre character varying NOT NULL,
    "contraseña" character varying NOT NULL
);
    DROP TABLE public.usuario;
       public         postgres    false    3            �            1259    27798    usuario_idusuario_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_idusuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.usuario_idusuario_seq;
       public       postgres    false    3    229            g           0    0    usuario_idusuario_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.usuario_idusuario_seq OWNED BY public.usuario.idusuario;
            public       postgres    false    230            �            1259    28829    vista_bodega    VIEW     w  CREATE VIEW public.vista_bodega AS
 SELECT productos.idproducto,
    categoria.categoria,
    productos.nombre,
    bodega.stock,
    bodega.costo,
    bodega.idsocio,
    productos.detalle
   FROM ((public.productos
     JOIN public.categoria ON ((productos.categoria = categoria.idcategoria)))
     LEFT JOIN public.bodega ON ((bodega.idproducto = productos.idproducto)));
    DROP VIEW public.vista_bodega;
       public       postgres    false    222    200    200    204    222    204    200    200    222    222    3            �            1259    27804 
   vista_caja    VIEW     �   CREATE VIEW public.vista_caja AS
 SELECT caja.idcaja,
    caja.fecha,
    caja.hora,
    caja.referencia,
    caja.pk_ventafactura_fk AS numerofactura,
    caja.valor
   FROM public.caja;
    DROP VIEW public.vista_caja;
       public       postgres    false    202    202    202    202    202    202    3            �            1259    28320    vista_cheques    VIEW     �  CREATE VIEW public.vista_cheques AS
 SELECT cheques.idcheque,
    cheques.n_cheque,
    cheques.cheque_banco,
    cheques.titular_cuenta,
    cheques.fecha_ingreso,
    cheques.fecha_deposito,
    cheques.valor,
    cheques.descripcion,
    cuenta_por_cobrar.n_factura
   FROM (public.cheques
     JOIN public.cuenta_por_cobrar ON ((cheques.pk_cuentaxcobrar_fk = cuenta_por_cobrar.idcuenta_por_cobrar)));
     DROP VIEW public.vista_cheques;
       public       postgres    false    206    209    209    206    206    206    206    206    206    206    206    3            �            1259    28277    vista_cuenta    VIEW       CREATE VIEW public.vista_cuenta AS
 SELECT cuenta.idcuenta,
    bancos.idbanco,
    bancos.nombre AS banco,
    cuenta.tipo,
    cuenta.ncuenta AS numero_cuenta,
    cuenta.nombre
   FROM (public.cuenta
     JOIN public.bancos ON ((cuenta.idbanco = bancos.idbanco)));
    DROP VIEW public.vista_cuenta;
       public       postgres    false    239    239    239    198    198    239    239    3            �            1259    27816    vista_cuentas_cobradas    VIEW     �  CREATE VIEW public.vista_cuentas_cobradas AS
 SELECT cuenta_por_cobrar.idcuenta_por_cobrar,
    factura.numerofactura,
    factura.cliente_cedula,
    cuenta_por_cobrar.valorpendiente,
    cuenta_por_cobrar.descripcion,
    cuenta_por_cobrar.estado
   FROM (public.cuenta_por_cobrar
     JOIN public.factura ON (((factura.numerofactura)::text = (cuenta_por_cobrar.n_factura)::text)))
  WHERE ((cuenta_por_cobrar.estado)::text = 'PAGADO'::text);
 )   DROP VIEW public.vista_cuentas_cobradas;
       public       postgres    false    209    209    209    209    209    218    218    3            �            1259    28396    vista_cuentas_pagadas    VIEW     .  CREATE VIEW public.vista_cuentas_pagadas AS
 SELECT cuentas_por_pagar.idcuentaxpagar,
    cuentas_por_pagar.fecha,
    cuentas_por_pagar.valor,
    cuentas_por_pagar.descripcion,
    cuentas_por_pagar.estado
   FROM public.cuentas_por_pagar
  WHERE ((cuentas_por_pagar.estado)::text = 'PAGADO'::text);
 (   DROP VIEW public.vista_cuentas_pagadas;
       public       postgres    false    211    211    211    211    211    3            �            1259    27824    vista_cuentas_por_cobrar    VIEW     �  CREATE VIEW public.vista_cuentas_por_cobrar AS
 SELECT cuenta_por_cobrar.idcuenta_por_cobrar,
    factura.numerofactura,
    factura.cliente_cedula,
    cuenta_por_cobrar.valorpendiente,
    cuenta_por_cobrar.descripcion,
    cuenta_por_cobrar.estado
   FROM (public.cuenta_por_cobrar
     JOIN public.factura ON (((factura.numerofactura)::text = (cuenta_por_cobrar.n_factura)::text)))
  WHERE ((cuenta_por_cobrar.estado)::text = 'PENDIENTE'::text);
 +   DROP VIEW public.vista_cuentas_por_cobrar;
       public       postgres    false    209    209    209    209    209    218    218    3            �            1259    28272    vista_depositos    VIEW     P  CREATE VIEW public.vista_depositos WITH (security_barrier='false') AS
 SELECT depositos.iddeposito,
    cuenta.nombre AS titular,
    cuenta.ncuenta,
    cuenta.tipo,
    depositos.n_deposito,
    depositos.fecha,
    depositos.efectivo,
    cheques.n_cheque,
    cheques.cheque_banco,
    cheques.descripcion AS detallecheque,
    cheques.valor,
    depositos.depositante,
    depositos.descripcion,
    depositos.total
   FROM ((public.depositos
     LEFT JOIN public.cheques ON ((cheques.idcheque = depositos.cheque)))
     JOIN public.cuenta ON ((cuenta.idcuenta = depositos.idcuenta)));
 "   DROP VIEW public.vista_depositos;
       public       postgres    false    213    213    213    213    239    239    239    239    213    206    206    206    206    206    213    213    213    213    3            �            1259    28838    vista_detalle_notaentrega    VIEW     M  CREATE VIEW public.vista_detalle_notaentrega AS
 SELECT notaentrega_bodega.idnotaentrega,
    notaentrega_bodega.idproducto,
    productos.nombre,
    notaentrega_bodega.cantidad,
    notaentrega_bodega.costo
   FROM (public.notaentrega_bodega
     JOIN public.productos ON ((notaentrega_bodega.idproducto = productos.idproducto)));
 ,   DROP VIEW public.vista_detalle_notaentrega;
       public       postgres    false    222    220    220    220    220    222    3            �            1259    27837    vista_detallefactura    VIEW     4  CREATE VIEW public.vista_detallefactura AS
 SELECT detallefactura.numerofactura,
    detallefactura.idproducto,
    productos.nombre,
    detallefactura.cantidad,
    detallefactura.preciounitario,
    detallefactura.preciototal
   FROM (public.detallefactura
     JOIN public.productos USING (idproducto));
 '   DROP VIEW public.vista_detallefactura;
       public       postgres    false    215    215    215    215    215    222    222    3            �            1259    27841    vista_devolucion_de_cliente    VIEW     �  CREATE VIEW public.vista_devolucion_de_cliente AS
 SELECT devolucion_de_cliente.numerofactura,
    devolucion_de_cliente.idproducto,
    productos.nombre,
    devolucion_de_cliente.cantidad,
    devolucion_de_cliente.descripcion,
    devolucion_de_cliente.fecha
   FROM (public.devolucion_de_cliente
     JOIN public.productos ON ((devolucion_de_cliente.idproducto = productos.idproducto)));
 .   DROP VIEW public.vista_devolucion_de_cliente;
       public       postgres    false    216    216    216    216    216    222    222    3            �            1259    27845    vista_devoluciones_a_socios    VIEW     p  CREATE VIEW public.vista_devoluciones_a_socios WITH (security_barrier='false') AS
 SELECT devolucionesasocios.idsocio,
    socios.nombre AS socio,
    devolucionesasocios.idproducto,
    productos.nombre AS producto,
    devolucionesasocios.fecha,
    devolucionesasocios.cantidad,
    devolucionesasocios.descripcion,
    bodega.costo
   FROM (((public.devolucionesasocios
     JOIN public.socios ON ((devolucionesasocios.idsocio = socios.idsocio)))
     JOIN public.productos ON ((devolucionesasocios.idproducto = productos.idproducto)))
     JOIN public.bodega ON ((devolucionesasocios.idproducto = bodega.idproducto)));
 .   DROP VIEW public.vista_devoluciones_a_socios;
       public       postgres    false    200    200    217    217    217    217    217    222    222    226    226    3            �            1259    27850    vista_factura    VIEW     �  CREATE VIEW public.vista_factura AS
 SELECT factura.numerofactura,
    factura.cliente_cedula,
    cliente.nombre,
    cliente.apellido,
    cliente.direccion,
    cliente.telefono,
    factura.fecha,
    factura.subtotal,
    factura.iva,
    factura.total,
    factura.credito
   FROM (public.factura
     JOIN public.cliente ON (((factura.cliente_cedula)::text = (cliente.cedula)::text)));
     DROP VIEW public.vista_factura;
       public       postgres    false    218    218    218    218    218    218    218    208    208    208    208    208    3            �            1259    28842    vista_notaentrega    VIEW       CREATE VIEW public.vista_notaentrega AS
 SELECT notaentrega.idnotadeeentrega,
    notaentrega.idsocio,
    socios.nombre,
    notaentrega.numero_nota_entrega,
    notaentrega.fecha,
    notaentrega.receptor
   FROM (public.notaentrega
     JOIN public.socios USING (idsocio));
 $   DROP VIEW public.vista_notaentrega;
       public       postgres    false    219    226    219    219    219    219    226    3            D           2604    27862    abonos idabonos    DEFAULT     r   ALTER TABLE ONLY public.abonos ALTER COLUMN idabonos SET DEFAULT nextval('public.abonos_idabonos_seq'::regclass);
 >   ALTER TABLE public.abonos ALTER COLUMN idabonos DROP DEFAULT;
       public       postgres    false    197    196            E           2604    27863    bancos idbanco    DEFAULT     p   ALTER TABLE ONLY public.bancos ALTER COLUMN idbanco SET DEFAULT nextval('public.bancos_idbanco_seq'::regclass);
 =   ALTER TABLE public.bancos ALTER COLUMN idbanco DROP DEFAULT;
       public       postgres    false    199    198            F           2604    27864    bodega idproducto    DEFAULT     v   ALTER TABLE ONLY public.bodega ALTER COLUMN idproducto SET DEFAULT nextval('public.bodega_idproducto_seq'::regclass);
 @   ALTER TABLE public.bodega ALTER COLUMN idproducto DROP DEFAULT;
       public       postgres    false    201    200            G           2604    27865    caja idcaja    DEFAULT     j   ALTER TABLE ONLY public.caja ALTER COLUMN idcaja SET DEFAULT nextval('public.caja_idcaja_seq'::regclass);
 :   ALTER TABLE public.caja ALTER COLUMN idcaja DROP DEFAULT;
       public       postgres    false    203    202            H           2604    27866    categoria idcategoria    DEFAULT     ~   ALTER TABLE ONLY public.categoria ALTER COLUMN idcategoria SET DEFAULT nextval('public.categoria_idcategoria_seq'::regclass);
 D   ALTER TABLE public.categoria ALTER COLUMN idcategoria DROP DEFAULT;
       public       postgres    false    205    204            I           2604    27867    cheques idcheque    DEFAULT     t   ALTER TABLE ONLY public.cheques ALTER COLUMN idcheque SET DEFAULT nextval('public.cheques_idcheque_seq'::regclass);
 ?   ALTER TABLE public.cheques ALTER COLUMN idcheque DROP DEFAULT;
       public       postgres    false    207    206            W           2604    28112    cuenta idcuenta    DEFAULT     r   ALTER TABLE ONLY public.cuenta ALTER COLUMN idcuenta SET DEFAULT nextval('public.cuenta_idcuenta_seq'::regclass);
 >   ALTER TABLE public.cuenta ALTER COLUMN idcuenta DROP DEFAULT;
       public       postgres    false    239    238    239            K           2604    27868 %   cuenta_por_cobrar idcuenta_por_cobrar    DEFAULT     �   ALTER TABLE ONLY public.cuenta_por_cobrar ALTER COLUMN idcuenta_por_cobrar SET DEFAULT nextval('public.cuenta_por_cobrar_idcuenta_por_cobrar_seq'::regclass);
 T   ALTER TABLE public.cuenta_por_cobrar ALTER COLUMN idcuenta_por_cobrar DROP DEFAULT;
       public       postgres    false    210    209            M           2604    27870     cuentas_por_pagar idcuentaxpagar    DEFAULT     �   ALTER TABLE ONLY public.cuentas_por_pagar ALTER COLUMN idcuentaxpagar SET DEFAULT nextval('public.cuentas_por_pagar_idcuentaxpagar_seq'::regclass);
 O   ALTER TABLE public.cuentas_por_pagar ALTER COLUMN idcuentaxpagar DROP DEFAULT;
       public       postgres    false    212    211            O           2604    27871    depositos iddeposito    DEFAULT     |   ALTER TABLE ONLY public.depositos ALTER COLUMN iddeposito SET DEFAULT nextval('public.depositos_iddeposito_seq'::regclass);
 C   ALTER TABLE public.depositos ALTER COLUMN iddeposito DROP DEFAULT;
       public       postgres    false    214    213            Y           2604    28251 $   movimientos_bancarios id_movimientos    DEFAULT     �   ALTER TABLE ONLY public.movimientos_bancarios ALTER COLUMN id_movimientos SET DEFAULT nextval('public.movimientos_bancarios_id_movimientos_seq'::regclass);
 S   ALTER TABLE public.movimientos_bancarios ALTER COLUMN id_movimientos DROP DEFAULT;
       public       postgres    false    241    242    242            Q           2604    27872    notaentrega idnotadeeentrega    DEFAULT     �   ALTER TABLE ONLY public.notaentrega ALTER COLUMN idnotadeeentrega SET DEFAULT nextval('public.notaentrega_idnotadeeentrega_seq'::regclass);
 K   ALTER TABLE public.notaentrega ALTER COLUMN idnotadeeentrega DROP DEFAULT;
       public       postgres    false    221    219            R           2604    27873    productos idproducto    DEFAULT     |   ALTER TABLE ONLY public.productos ALTER COLUMN idproducto SET DEFAULT nextval('public.productos_idproducto_seq'::regclass);
 C   ALTER TABLE public.productos ALTER COLUMN idproducto DROP DEFAULT;
       public       postgres    false    223    222            S           2604    27874    retiros idretiros    DEFAULT     v   ALTER TABLE ONLY public.retiros ALTER COLUMN idretiros SET DEFAULT nextval('public.retiros_idretiros_seq'::regclass);
 @   ALTER TABLE public.retiros ALTER COLUMN idretiros DROP DEFAULT;
       public       postgres    false    225    224            U           2604    27875    socios idsocio    DEFAULT     p   ALTER TABLE ONLY public.socios ALTER COLUMN idsocio SET DEFAULT nextval('public.socios_idsocio_seq'::regclass);
 =   ALTER TABLE public.socios ALTER COLUMN idsocio DROP DEFAULT;
       public       postgres    false    227    226            V           2604    27876    usuario idusuario    DEFAULT     v   ALTER TABLE ONLY public.usuario ALTER COLUMN idusuario SET DEFAULT nextval('public.usuario_idusuario_seq'::regclass);
 @   ALTER TABLE public.usuario ALTER COLUMN idusuario DROP DEFAULT;
       public       postgres    false    230    229            O          0    38696    AAA 
   TABLE DATA               &   COPY public."AAA" ("DSA") FROM stdin;
    public       postgres    false    250   1      (          0    27640    abonos 
   TABLE DATA               X   COPY public.abonos (idabonos, pk_cuentas_por_cobrar_fk, valor, descripcion) FROM stdin;
    public       postgres    false    196   N      *          0    27648    bancos 
   TABLE DATA               >   COPY public.bancos (idbanco, nombre, descripcion) FROM stdin;
    public       postgres    false    198   �      ,          0    27656    bodega 
   TABLE DATA               C   COPY public.bodega (idproducto, idsocio, stock, costo) FROM stdin;
    public       postgres    false    200   M      .          0    27661    caja 
   TABLE DATA               Z   COPY public.caja (idcaja, pk_ventafactura_fk, fecha, hora, valor, referencia) FROM stdin;
    public       postgres    false    202   �      0          0    27669 	   categoria 
   TABLE DATA               ;   COPY public.categoria (idcategoria, categoria) FROM stdin;
    public       postgres    false    204   E       2          0    27677    cheques 
   TABLE DATA               �   COPY public.cheques (idcheque, pk_cuentaxcobrar_fk, n_cheque, cheque_banco, titular_cuenta, fecha_ingreso, fecha_deposito, valor, descripcion) FROM stdin;
    public       postgres    false    206   �       4          0    27685    cliente 
   TABLE DATA               P   COPY public.cliente (cedula, nombre, apellido, direccion, telefono) FROM stdin;
    public       postgres    false    208   �!      L          0    28109    cuenta 
   TABLE DATA               J   COPY public.cuenta (idcuenta, idbanco, tipo, ncuenta, nombre) FROM stdin;
    public       postgres    false    239   c"      5          0    27691    cuenta_por_cobrar 
   TABLE DATA               p   COPY public.cuenta_por_cobrar (idcuenta_por_cobrar, n_factura, valorpendiente, descripcion, estado) FROM stdin;
    public       postgres    false    209   �"      7          0    27708    cuentas_por_pagar 
   TABLE DATA               ^   COPY public.cuentas_por_pagar (idcuentaxpagar, fecha, valor, descripcion, estado) FROM stdin;
    public       postgres    false    211   �#      9          0    27717 	   depositos 
   TABLE DATA                  COPY public.depositos (iddeposito, idcuenta, n_deposito, fecha, efectivo, cheque, depositante, descripcion, total) FROM stdin;
    public       postgres    false    213   $      ;          0    27726    detallefactura 
   TABLE DATA               j   COPY public.detallefactura (numerofactura, idproducto, cantidad, preciounitario, preciototal) FROM stdin;
    public       postgres    false    215   &%      <          0    27732    devolucion_de_cliente 
   TABLE DATA               h   COPY public.devolucion_de_cliente (numerofactura, idproducto, cantidad, descripcion, fecha) FROM stdin;
    public       postgres    false    216   �%      =          0    27738    devolucionesasocios 
   TABLE DATA               `   COPY public.devolucionesasocios (idsocio, idproducto, fecha, cantidad, descripcion) FROM stdin;
    public       postgres    false    217   &      >          0    27744    factura 
   TABLE DATA               f   COPY public.factura (numerofactura, cliente_cedula, fecha, subtotal, iva, total, credito) FROM stdin;
    public       postgres    false    218   }&      N          0    28248    movimientos_bancarios 
   TABLE DATA               ~   COPY public.movimientos_bancarios (id_movimientos, codigo, idcuenta, fecha, descripcion, deposito, retiro, saldo) FROM stdin;
    public       postgres    false    242   �'      ?          0    27750    notaentrega 
   TABLE DATA               f   COPY public.notaentrega (idnotadeeentrega, idsocio, numero_nota_entrega, fecha, receptor) FROM stdin;
    public       postgres    false    219   �(      @          0    27756    notaentrega_bodega 
   TABLE DATA               X   COPY public.notaentrega_bodega (idnotaentrega, idproducto, cantidad, costo) FROM stdin;
    public       postgres    false    220   �)      B          0    27761 	   productos 
   TABLE DATA               K   COPY public.productos (idproducto, categoria, nombre, detalle) FROM stdin;
    public       postgres    false    222   �)      D          0    27769    retiros 
   TABLE DATA               Q   COPY public.retiros (idretiros, idcuenta, fecha, descripcion, valor) FROM stdin;
    public       postgres    false    224   �*      F          0    27778    socios 
   TABLE DATA               B   COPY public.socios (idsocio, ruc, nombre, referencia) FROM stdin;
    public       postgres    false    226   �*      H          0    27786 	   telefonos 
   TABLE DATA               B   COPY public.telefonos (idsocio, telefono, referencia) FROM stdin;
    public       postgres    false    228   d+      I          0    27792    usuario 
   TABLE DATA               C   COPY public.usuario (idusuario, nombre, "contraseña") FROM stdin;
    public       postgres    false    229   �+      h           0    0    abonos_idabonos_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.abonos_idabonos_seq', 12, true);
            public       postgres    false    197            i           0    0    bancos_idbanco_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.bancos_idbanco_seq', 8, true);
            public       postgres    false    199            j           0    0    bodega_idproducto_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.bodega_idproducto_seq', 1, false);
            public       postgres    false    201            k           0    0    caja_idcaja_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.caja_idcaja_seq', 19, true);
            public       postgres    false    203            l           0    0    categoria_idcategoria_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.categoria_idcategoria_seq', 7, true);
            public       postgres    false    205            m           0    0    cheques_idcheque_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.cheques_idcheque_seq', 8, true);
            public       postgres    false    207            n           0    0    cuenta_idcuenta_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.cuenta_idcuenta_seq', 4, true);
            public       postgres    false    238            o           0    0 )   cuenta_por_cobrar_idcuenta_por_cobrar_seq    SEQUENCE SET     W   SELECT pg_catalog.setval('public.cuenta_por_cobrar_idcuenta_por_cobrar_seq', 7, true);
            public       postgres    false    210            p           0    0 $   cuentas_por_pagar_idcuentaxpagar_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('public.cuentas_por_pagar_idcuentaxpagar_seq', 5, true);
            public       postgres    false    212            q           0    0    depositos_iddeposito_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.depositos_iddeposito_seq', 16, true);
            public       postgres    false    214            r           0    0 (   movimientos_bancarios_id_movimientos_seq    SEQUENCE SET     W   SELECT pg_catalog.setval('public.movimientos_bancarios_id_movimientos_seq', 21, true);
            public       postgres    false    241            s           0    0     notaentrega_idnotadeeentrega_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.notaentrega_idnotadeeentrega_seq', 3, true);
            public       postgres    false    221            t           0    0    productos_idproducto_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.productos_idproducto_seq', 18, true);
            public       postgres    false    223            u           0    0    retiros_idretiros_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.retiros_idretiros_seq', 3, true);
            public       postgres    false    225            v           0    0    socios_idsocio_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.socios_idsocio_seq', 1, false);
            public       postgres    false    227            w           0    0    usuario_idusuario_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.usuario_idusuario_seq', 1, false);
            public       postgres    false    230            ]           2606    27878    abonos abonos_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.abonos
    ADD CONSTRAINT abonos_pkey PRIMARY KEY (idabonos);
 <   ALTER TABLE ONLY public.abonos DROP CONSTRAINT abonos_pkey;
       public         postgres    false    196            _           2606    27880    bancos bancos_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.bancos
    ADD CONSTRAINT bancos_pkey PRIMARY KEY (idbanco);
 <   ALTER TABLE ONLY public.bancos DROP CONSTRAINT bancos_pkey;
       public         postgres    false    198            a           2606    27882    bodega bodega_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.bodega
    ADD CONSTRAINT bodega_pkey PRIMARY KEY (idproducto);
 <   ALTER TABLE ONLY public.bodega DROP CONSTRAINT bodega_pkey;
       public         postgres    false    200            c           2606    27884    caja caja_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.caja
    ADD CONSTRAINT caja_pkey PRIMARY KEY (idcaja);
 8   ALTER TABLE ONLY public.caja DROP CONSTRAINT caja_pkey;
       public         postgres    false    202            e           2606    27886    categoria categoria_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);
 B   ALTER TABLE ONLY public.categoria DROP CONSTRAINT categoria_pkey;
       public         postgres    false    204            g           2606    27888    cheques cheques_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.cheques
    ADD CONSTRAINT cheques_pkey PRIMARY KEY (idcheque);
 >   ALTER TABLE ONLY public.cheques DROP CONSTRAINT cheques_pkey;
       public         postgres    false    206            i           2606    27890    cliente cliente_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (cedula);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public         postgres    false    208            }           2606    28114    cuenta cuenta_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (idcuenta);
 <   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT cuenta_pkey;
       public         postgres    false    239            k           2606    27892 (   cuenta_por_cobrar cuenta_por_cobrar_pkey 
   CONSTRAINT     w   ALTER TABLE ONLY public.cuenta_por_cobrar
    ADD CONSTRAINT cuenta_por_cobrar_pkey PRIMARY KEY (idcuenta_por_cobrar);
 R   ALTER TABLE ONLY public.cuenta_por_cobrar DROP CONSTRAINT cuenta_por_cobrar_pkey;
       public         postgres    false    209            X           2606    28271    cuenta cuenta_tipo_check    CHECK CONSTRAINT     �   ALTER TABLE public.cuenta
    ADD CONSTRAINT cuenta_tipo_check CHECK (((tipo)::text = ANY (ARRAY[('CUENTA AHORROS'::character varying)::text, ('CUENTA CORRIENTE'::character varying)::text]))) NOT VALID;
 =   ALTER TABLE public.cuenta DROP CONSTRAINT cuenta_tipo_check;
       public       postgres    false    239    239            m           2606    27894 &   cuentas_por_pagar cuentas_pagadas_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.cuentas_por_pagar
    ADD CONSTRAINT cuentas_pagadas_pkey PRIMARY KEY (idcuentaxpagar);
 P   ALTER TABLE ONLY public.cuentas_por_pagar DROP CONSTRAINT cuentas_pagadas_pkey;
       public         postgres    false    211            o           2606    27898    depositos depositos_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.depositos
    ADD CONSTRAINT depositos_pkey PRIMARY KEY (iddeposito);
 B   ALTER TABLE ONLY public.depositos DROP CONSTRAINT depositos_pkey;
       public         postgres    false    213            J           2606    27899    cheques descripcion    CHECK CONSTRAINT     �   ALTER TABLE public.cheques
    ADD CONSTRAINT descripcion CHECK (((descripcion)::text = ANY (ARRAY[('DEPOSITADO'::character varying)::text, ('PENDIENTE'::character varying)::text]))) NOT VALID;
 8   ALTER TABLE public.cheques DROP CONSTRAINT descripcion;
       public       postgres    false    206    206            q           2606    27901    factura factura_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (numerofactura);
 >   ALTER TABLE ONLY public.factura DROP CONSTRAINT factura_pkey;
       public         postgres    false    218                       2606    28255 0   movimientos_bancarios movimientos_bancarios_pkey 
   CONSTRAINT     z   ALTER TABLE ONLY public.movimientos_bancarios
    ADD CONSTRAINT movimientos_bancarios_pkey PRIMARY KEY (id_movimientos);
 Z   ALTER TABLE ONLY public.movimientos_bancarios DROP CONSTRAINT movimientos_bancarios_pkey;
       public         postgres    false    242            s           2606    27903    notaentrega notaentrega_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.notaentrega
    ADD CONSTRAINT notaentrega_pkey PRIMARY KEY (idnotadeeentrega);
 F   ALTER TABLE ONLY public.notaentrega DROP CONSTRAINT notaentrega_pkey;
       public         postgres    false    219            u           2606    27905    productos productos_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.productos
    ADD CONSTRAINT productos_pkey PRIMARY KEY (idproducto);
 B   ALTER TABLE ONLY public.productos DROP CONSTRAINT productos_pkey;
       public         postgres    false    222            w           2606    27907    retiros retiros_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.retiros
    ADD CONSTRAINT retiros_pkey PRIMARY KEY (idretiros);
 >   ALTER TABLE ONLY public.retiros DROP CONSTRAINT retiros_pkey;
       public         postgres    false    224            y           2606    27909    socios socios_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.socios
    ADD CONSTRAINT socios_pkey PRIMARY KEY (idsocio);
 <   ALTER TABLE ONLY public.socios DROP CONSTRAINT socios_pkey;
       public         postgres    false    226            {           2606    27911    usuario usuario_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public         postgres    false    229            �           2620    28269 #   depositos sp_insertardeposito_trans    TRIGGER     �   CREATE TRIGGER sp_insertardeposito_trans AFTER INSERT ON public.depositos FOR EACH ROW EXECUTE PROCEDURE public.sp_insert_trans_depo();
 <   DROP TRIGGER sp_insertardeposito_trans ON public.depositos;
       public       postgres    false    274    213            �           2620    28282 !   retiros sp_insertardeposito_trans    TRIGGER     �   CREATE TRIGGER sp_insertardeposito_trans AFTER INSERT ON public.retiros FOR EACH ROW EXECUTE PROCEDURE public.sp_insert_trans_retiro();
 :   DROP TRIGGER sp_insertardeposito_trans ON public.retiros;
       public       postgres    false    224    259            �           2620    27912    detallefactura tr_update    TRIGGER        CREATE TRIGGER tr_update AFTER INSERT ON public.detallefactura FOR EACH ROW EXECUTE PROCEDURE public.sp_actualizarstockfact();
 1   DROP TRIGGER tr_update ON public.detallefactura;
       public       postgres    false    253    215            �           2620    28404    cheques tr_update    TRIGGER     �   CREATE TRIGGER tr_update AFTER INSERT ON public.cheques FOR EACH ROW EXECUTE PROCEDURE public.sp_actualizarcuentasporcobrarconcheque();
 *   DROP TRIGGER tr_update ON public.cheques;
       public       postgres    false    267    206            �           2620    27913 &   notaentrega_bodega tr_update_bodeg_soc    TRIGGER     �   CREATE TRIGGER tr_update_bodeg_soc AFTER INSERT ON public.notaentrega_bodega FOR EACH ROW EXECUTE PROCEDURE public.sp_actualizarstocksocios();
 ?   DROP TRIGGER tr_update_bodeg_soc ON public.notaentrega_bodega;
       public       postgres    false    272    220            �           2620    27914 )   devolucion_de_cliente tr_update_bodeg_soc    TRIGGER     �   CREATE TRIGGER tr_update_bodeg_soc AFTER INSERT ON public.devolucion_de_cliente FOR EACH ROW EXECUTE PROCEDURE public.sp_actualizarstockdevclientes();
 B   DROP TRIGGER tr_update_bodeg_soc ON public.devolucion_de_cliente;
       public       postgres    false    216    269            �           2620    27915 '   devolucionesasocios tr_update_bodeg_soc    TRIGGER     �   CREATE TRIGGER tr_update_bodeg_soc AFTER INSERT ON public.devolucionesasocios FOR EACH ROW EXECUTE PROCEDURE public.sp_actualizarstockdevsoc();
 @   DROP TRIGGER tr_update_bodeg_soc ON public.devolucionesasocios;
       public       postgres    false    217    254            �           2620    27916    factura tr_updatecaja    TRIGGER     w   CREATE TRIGGER tr_updatecaja AFTER INSERT ON public.factura FOR EACH ROW EXECUTE PROCEDURE public.sp_actualizarcaja();
 .   DROP TRIGGER tr_updatecaja ON public.factura;
       public       postgres    false    262    218            �           2620    27917    abonos tr_updatecuentascobradas    TRIGGER     �   CREATE TRIGGER tr_updatecuentascobradas AFTER INSERT ON public.abonos FOR EACH ROW EXECUTE PROCEDURE public.sp_actualizarcuentascobradas();
 8   DROP TRIGGER tr_updatecuentascobradas ON public.abonos;
       public       postgres    false    252    196            �           2606    28315 (   cheques cheques_pk_cuentaxcobrar_fk_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cheques
    ADD CONSTRAINT cheques_pk_cuentaxcobrar_fk_fkey FOREIGN KEY (pk_cuentaxcobrar_fk) REFERENCES public.cuenta_por_cobrar(idcuenta_por_cobrar);
 R   ALTER TABLE ONLY public.cheques DROP CONSTRAINT cheques_pk_cuentaxcobrar_fk_fkey;
       public       postgres    false    2923    209    206            �           2606    28124    depositos cuenta_fk    FK CONSTRAINT     z   ALTER TABLE ONLY public.depositos
    ADD CONSTRAINT cuenta_fk FOREIGN KEY (idcuenta) REFERENCES public.cuenta(idcuenta);
 =   ALTER TABLE ONLY public.depositos DROP CONSTRAINT cuenta_fk;
       public       postgres    false    239    2941    213            �           2606    28129    cuenta cuenta_idbanco_fkey    FK CONSTRAINT        ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_idbanco_fkey FOREIGN KEY (idbanco) REFERENCES public.bancos(idbanco);
 D   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT cuenta_idbanco_fkey;
       public       postgres    false    198    239    2911            �           2606    28301 2   cuenta_por_cobrar cuenta_por_cobrar_n_factura_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cuenta_por_cobrar
    ADD CONSTRAINT cuenta_por_cobrar_n_factura_fkey FOREIGN KEY (n_factura) REFERENCES public.factura(numerofactura);
 \   ALTER TABLE ONLY public.cuenta_por_cobrar DROP CONSTRAINT cuenta_por_cobrar_n_factura_fkey;
       public       postgres    false    209    218    2929            �           2606    28094    depositos depositos_cheque_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.depositos
    ADD CONSTRAINT depositos_cheque_fkey FOREIGN KEY (cheque) REFERENCES public.cheques(idcheque);
 I   ALTER TABLE ONLY public.depositos DROP CONSTRAINT depositos_cheque_fkey;
       public       postgres    false    206    213    2919            �           2606    28256 9   movimientos_bancarios movimientos_bancarios_idcuenta_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.movimientos_bancarios
    ADD CONSTRAINT movimientos_bancarios_idcuenta_fkey FOREIGN KEY (idcuenta) REFERENCES public.cuenta(idcuenta);
 c   ALTER TABLE ONLY public.movimientos_bancarios DROP CONSTRAINT movimientos_bancarios_idcuenta_fkey;
       public       postgres    false    2941    242    239            �           2606    28833 5   notaentrega_bodega notaentrega_bodega_idproducto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.notaentrega_bodega
    ADD CONSTRAINT notaentrega_bodega_idproducto_fkey FOREIGN KEY (idproducto) REFERENCES public.productos(idproducto);
 _   ALTER TABLE ONLY public.notaentrega_bodega DROP CONSTRAINT notaentrega_bodega_idproducto_fkey;
       public       postgres    false    220    2933    222            �           2606    27923    abonos pk_cuentas_por_cobrar_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.abonos
    ADD CONSTRAINT pk_cuentas_por_cobrar_fk FOREIGN KEY (pk_cuentas_por_cobrar_fk) REFERENCES public.cuenta_por_cobrar(idcuenta_por_cobrar);
 I   ALTER TABLE ONLY public.abonos DROP CONSTRAINT pk_cuentas_por_cobrar_fk;
       public       postgres    false    2923    196    209            �           2606    27928    bodega ref_bodega_to_productos    FK CONSTRAINT     �   ALTER TABLE ONLY public.bodega
    ADD CONSTRAINT ref_bodega_to_productos FOREIGN KEY (idproducto) REFERENCES public.productos(idproducto);
 H   ALTER TABLE ONLY public.bodega DROP CONSTRAINT ref_bodega_to_productos;
       public       postgres    false    222    200    2933            �           2606    27933    bodega ref_bodega_to_socios    FK CONSTRAINT     �   ALTER TABLE ONLY public.bodega
    ADD CONSTRAINT ref_bodega_to_socios FOREIGN KEY (idsocio) REFERENCES public.socios(idsocio);
 E   ALTER TABLE ONLY public.bodega DROP CONSTRAINT ref_bodega_to_socios;
       public       postgres    false    200    226    2937            �           2606    27938    caja ref_caja_to_factura    FK CONSTRAINT     �   ALTER TABLE ONLY public.caja
    ADD CONSTRAINT ref_caja_to_factura FOREIGN KEY (pk_ventafactura_fk) REFERENCES public.factura(numerofactura);
 B   ALTER TABLE ONLY public.caja DROP CONSTRAINT ref_caja_to_factura;
       public       postgres    false    202    2929    218            �           2606    27953 +   detallefactura ref_detallefactura_to_bodega    FK CONSTRAINT     �   ALTER TABLE ONLY public.detallefactura
    ADD CONSTRAINT ref_detallefactura_to_bodega FOREIGN KEY (idproducto) REFERENCES public.bodega(idproducto);
 U   ALTER TABLE ONLY public.detallefactura DROP CONSTRAINT ref_detallefactura_to_bodega;
       public       postgres    false    215    200    2913            �           2606    27958 ,   detallefactura ref_detallefactura_to_factura    FK CONSTRAINT     �   ALTER TABLE ONLY public.detallefactura
    ADD CONSTRAINT ref_detallefactura_to_factura FOREIGN KEY (numerofactura) REFERENCES public.factura(numerofactura);
 V   ALTER TABLE ONLY public.detallefactura DROP CONSTRAINT ref_detallefactura_to_factura;
       public       postgres    false    218    2929    215            �           2606    27963 9   devolucion_de_cliente ref_devolucion_de_cliente_to_bodega    FK CONSTRAINT     �   ALTER TABLE ONLY public.devolucion_de_cliente
    ADD CONSTRAINT ref_devolucion_de_cliente_to_bodega FOREIGN KEY (idproducto) REFERENCES public.bodega(idproducto);
 c   ALTER TABLE ONLY public.devolucion_de_cliente DROP CONSTRAINT ref_devolucion_de_cliente_to_bodega;
       public       postgres    false    216    200    2913            �           2606    27968 :   devolucion_de_cliente ref_devolucion_de_cliente_to_factura    FK CONSTRAINT     �   ALTER TABLE ONLY public.devolucion_de_cliente
    ADD CONSTRAINT ref_devolucion_de_cliente_to_factura FOREIGN KEY (numerofactura) REFERENCES public.factura(numerofactura);
 d   ALTER TABLE ONLY public.devolucion_de_cliente DROP CONSTRAINT ref_devolucion_de_cliente_to_factura;
       public       postgres    false    216    2929    218            �           2606    27973 5   devolucionesasocios ref_devolucionesasocios_to_bodega    FK CONSTRAINT     �   ALTER TABLE ONLY public.devolucionesasocios
    ADD CONSTRAINT ref_devolucionesasocios_to_bodega FOREIGN KEY (idproducto) REFERENCES public.bodega(idproducto);
 _   ALTER TABLE ONLY public.devolucionesasocios DROP CONSTRAINT ref_devolucionesasocios_to_bodega;
       public       postgres    false    2913    200    217            �           2606    27978 5   devolucionesasocios ref_devolucionesasocios_to_socios    FK CONSTRAINT     �   ALTER TABLE ONLY public.devolucionesasocios
    ADD CONSTRAINT ref_devolucionesasocios_to_socios FOREIGN KEY (idsocio) REFERENCES public.socios(idsocio);
 _   ALTER TABLE ONLY public.devolucionesasocios DROP CONSTRAINT ref_devolucionesasocios_to_socios;
       public       postgres    false    217    226    2937            �           2606    27983    factura ref_factura_to_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT ref_factura_to_cliente FOREIGN KEY (cliente_cedula) REFERENCES public.cliente(cedula) ON UPDATE CASCADE;
 H   ALTER TABLE ONLY public.factura DROP CONSTRAINT ref_factura_to_cliente;
       public       postgres    false    218    208    2921            �           2606    27993 %   notaentrega ref_notaentrega_to_socios    FK CONSTRAINT     �   ALTER TABLE ONLY public.notaentrega
    ADD CONSTRAINT ref_notaentrega_to_socios FOREIGN KEY (idsocio) REFERENCES public.socios(idsocio);
 O   ALTER TABLE ONLY public.notaentrega DROP CONSTRAINT ref_notaentrega_to_socios;
       public       postgres    false    219    2937    226            �           2606    27998 $   productos ref_productos_to_categoria    FK CONSTRAINT     �   ALTER TABLE ONLY public.productos
    ADD CONSTRAINT ref_productos_to_categoria FOREIGN KEY (categoria) REFERENCES public.categoria(idcategoria);
 N   ALTER TABLE ONLY public.productos DROP CONSTRAINT ref_productos_to_categoria;
       public       postgres    false    2917    204    222            �           2606    28003 5   notaentrega_bodega ref_socio_productos_to_notaentrega    FK CONSTRAINT     �   ALTER TABLE ONLY public.notaentrega_bodega
    ADD CONSTRAINT ref_socio_productos_to_notaentrega FOREIGN KEY (idnotaentrega) REFERENCES public.notaentrega(idnotadeeentrega);
 _   ALTER TABLE ONLY public.notaentrega_bodega DROP CONSTRAINT ref_socio_productos_to_notaentrega;
       public       postgres    false    2931    219    220            �           2606    28008 !   telefonos ref_telefonos_to_socios    FK CONSTRAINT     �   ALTER TABLE ONLY public.telefonos
    ADD CONSTRAINT ref_telefonos_to_socios FOREIGN KEY (idsocio) REFERENCES public.socios(idsocio);
 K   ALTER TABLE ONLY public.telefonos DROP CONSTRAINT ref_telefonos_to_socios;
       public       postgres    false    2937    226    228            �           2606    28144    retiros retiros_idcuenta_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.retiros
    ADD CONSTRAINT retiros_idcuenta_fkey FOREIGN KEY (idcuenta) REFERENCES public.cuenta(idcuenta);
 G   ALTER TABLE ONLY public.retiros DROP CONSTRAINT retiros_idcuenta_fkey;
       public       postgres    false    2941    224    239            O      x������ � �      (   j   x�3�4�41�tt��sTp�bWg߀ G.KN#NcK=#d`����ЀӘ�� C�h����%*�LL���24�4�4��35[��������������� 6J      *   u   x�U�K!��5��O`��m٢��������
�헿jm��tq�PsIj�`7_�[��*��v���v���	����6��*܏���Ϣ�Ѱjt	E���ņ�?��4�|YYk�Ox,U      ,   G   x�=���0c(�#�U����\�lga�Hh!���!���ve�ҙ1L=����7_�rn5e����!��U�      .   �  x�u�Mn�0���)|���v��-
q���Yt�z��q��m >���CV�lnr�PQ!�O����/��EDW� R�PY!&O�x�"���L����zRA���V�m�	c�X�xUI�f�a>��qv��<��&x�������n²�RE�Cp~w�{V@�Bm��8�]�!�U�ӵ5�J�*>���\ȗ�:/WY�WY��Ur�!�fmO^;����Ԩa0pf�a7�&�ص�f_��#a�.�\���?y��qd�n��7��sLŮ������:�EF��������� ��q�������e~���x|�vq0>�>��rͿ��{�*�F��xr�~|�`V8���Ai�� Y���Gw(����uZ���r���(^n��+�7����A      0   G   x�3��uu�t�2���<<ё�L�s�p��z�qsz��:�r�q::;��y�s�s���=... Tu      2   �   x�mQ[�� ���"h�#��	LZ"6dI�Ti���US�� $a�m8�'\��5�_��h6���A0>�?��99����@��vsC�*����%��������Q��(��"A�`�U����MB0%�Ý&|X���a%�Ml.�Ο|X]�%LÊ �t�<�X� (����7n�'���B��� 420�>]�'����9S��O�jCef�\�v��1�}[��9WU�qjW      4   �   x�-�A�� E�p
��r��b������Q��B՝���ƀ3�����`Tnb�q��pM��Ȭ�n�aqH:"�3��b����7����=�	|r<�.�����[:Okp�M�Md�,�c���喴'��\�>�C��Lc��qÏ�HK�oz&�J�s�,�ܤ$�0���a]�l�k��8�      L   h   x�e�A
� ����)r��
�r0$�&Y���Y�Ń�=!�|ض뢟�{$�9� Zu+V��5���ӖYF�� ���?O)��0/tj�^��:�z1�6� �      5   �   x���M
1F��)r -If�28E*ږq���?�uP���,�ǃ��̸Ƽ
��8��@��LE9@">��) 
ڇ.{0o�6Ti�k�x��q�t���3��i��;%3A��Q��{N�9G`����]j蜆,��J�Sj��F����C+��T�P�      7   �   x�e�1
� E��^ a�H9� �*�6���k
�"݇�_�F�M�'eaE�t$���i�c�Wr��d��s�,��iX�R)�t��0W��O�h4��rq�|,����>7`,��̩�+�p~���0�      9   �   x���M��0���)� U�iXVL)��JՂ���1��L�6��M����$��ԋ
y'��4�]�QveM��m��<�x�6 μ��N����9x�Rc<z`�I}}6�u�!M��fņ@È*��8>Ys�66jà�c�Rf	#�w�8�F�^���`aq�ū�aC��D���X��Y�8�Ac�i�����v��k���0��������E�s	�G�9����fW,NE�]٣�Lx��7���1�7k삅      ;   �   x�u��� D��]�������9�ZH���{ P�e�.8s�ܸ��d���Ic+�]���ر���yT��U��Ӗ&nM՜��]y,��������e��H?��N�6�����c��x�Rޔ�AF      <   O   x�30000��GG� ��P��9�-uuL��J�9M�j|]��]\�<\\�\�CB�����s��qqq ��      =   P   x�3�4�420��50�54�4�r�sqVpqusu	�v�2���9��AΎ.�A��
~�
��
a@M�\1z\\\ ��D      >   -  x���Kn�0D��)zCR��9A�9A��vG�*����03��	µ$�$ඁ7(e�K�N�"�V�~��3C.'Q�s�ʣ�/��d�KJ�̀jL='Q�L�p�r�C�pa�IAs�qu��e�f;��7��K�봓��y�2�|b�������O]�|	��[l��^j�ز[y3\]1
q	G�ǿ�O�����X�x��������݁Y�R�=쾹׺�X��+��F���Վ���m�����<���7���i���j4�x_2�{�_.�G潬4�a��{�{!|j��      N   .  x��Sˎ�0<'_��A 9oY���fQi�w��ֱ��P���dfƎ=���h�`���]��P���j:�}YR0��o7���`�.�6��\5��!e����j�sͨPz	S����.j H��z�Ӑ��˧��8�B$#�8�k-�ʭ]�O�C֋n���m7!�f^X�0��2�Y��u����"G\[&�b#rM)J���OgCJ�� {%�
y<�������[!�U�!���.,؝�c���p��O6��c��9���b^�쥟��\W��n�<X��&<w�g��p�ػ�������      ?   �   x�U�K�0D��)r� ��E[)RHP���9p����n�5cv�%GR(��n�����x��n��PA�V�߁���=[Vl�#`QI��Hd�i��w���˄��!N�����s��^�G-����<H%5      @   F   x�E���0��]�^	���	~��`�/\�ъ��c~ZR�VW�+�y�����{�9��P|.�/v�U      B   �   x�U�=� ��ٜ"'��#%$"\Zu���Q24v��yeK��9���&X\t�'�TݒjREڑv�=��n_���� ��x`|�D���D�e�Y�d;ԭ���A�����x\,�33
��Y�yXY��2n�X̥���${���[�}oB���[r      D   >   x�Mɡ�@@���S_ � �� �]�Hy�,3��㼃cv�a�yạn���R}      F   v   x�%�K�0 �ur�� �㐰4�	Eő�
���AM�o�	�J���`�K���E�e6�Q9�Z��o��qe8<��\S�d��<��>���j�E)����L+�H�ӓ������u��      H   l   x�M�K
A��u�0����,W�20�+������|UҸzE��zߎ�d�H�P���߷�k;HG�JQ,8SO�� ~s�2l������mB7C/^p�1Na�~.D���#      I      x������ � �     