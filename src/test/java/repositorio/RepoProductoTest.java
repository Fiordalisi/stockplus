package repositorio;

import internal.negocio.Producto;
import internal.repositorio.RepoProducto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import java.sql.PreparedStatement;

class RepoProductoTest {
    private Connection mockConn;
    private PreparedStatement mockPreparedStatement;
    private Statement mockStatement;
    private ResultSet mockResultSet;
    private RepoProducto repoProducto;

    @BeforeEach
    void setUp() throws Exception {
        // Mockear la conexión, el PreparedStatement, el Statement y el ResultSet
        mockConn = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockStatement = Mockito.mock(Statement.class);
        mockResultSet = Mockito.mock(ResultSet.class);

        // Configurar el comportamiento de la conexión para devolver el PreparedStatement y Statement mockeados
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockConn.createStatement()).thenReturn(mockStatement);

        // Instanciar RepoProducto con el mock de conexión
        repoProducto = new RepoProducto(mockConn);
    }

    @Test
    void testAgregarProducto() throws Exception {
        Producto producto = new Producto(
                "ProductoPrueba",
                "Descripción de prueba",
                "unidad",
                10,
                100.0,
                "1",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis())
        );


        repoProducto.agregar(producto);

        // se podria mejorar para probar la query completa, pero en esta caso sirve para verificar un insert
        verify(mockConn).prepareStatement(startsWith("INSERT INTO Productos"));


        verify(mockPreparedStatement).setInt(eq(1), anyInt());
        verify(mockPreparedStatement).setString(2, "ProductoPrueba");
        verify(mockPreparedStatement).setString(3, "Descripción de prueba");
        verify(mockPreparedStatement).setString(4, "unidad");
        verify(mockPreparedStatement).setInt(5, 10);
        verify(mockPreparedStatement).setDouble(6, 100.0);
        verify(mockPreparedStatement).setInt(7, producto.getLimiteMinimo());
        verify(mockPreparedStatement).setInt(8, producto.getCantidadDeReposicion());
        verify(mockPreparedStatement).setDate(eq(9), any(Date.class));
        verify(mockPreparedStatement).setDate(eq(10), any(Date.class));


        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testBuscarProducto() throws Exception {
        // Configurar el comportamiento del ResultSet simulado para devolver datos cuando se llama a "buscar"
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);  // Simular que se encuentra un producto
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("nombre")).thenReturn("ProductoPrueba");
        when(mockResultSet.getString("descripcion")).thenReturn("Descripción de prueba");
        when(mockResultSet.getString("unidad_de_medida")).thenReturn("unidad");
        when(mockResultSet.getInt("stock")).thenReturn(10);
        when(mockResultSet.getDouble("precio_unitario")).thenReturn(100.0);
        when(mockResultSet.getInt("limite_minimo")).thenReturn(5);
        when(mockResultSet.getInt("cantidad_de_reposicion")).thenReturn(10);
        when(mockResultSet.getInt("categoria_id")).thenReturn(1);
        when(mockResultSet.getDate("fecha_de_creacion")).thenReturn(Date.valueOf("2023-01-01"));
        when(mockResultSet.getDate("fecha_de_modificacion")).thenReturn(Date.valueOf("2023-01-01"));

        // Llamar al método que se desea probar
        Producto producto = repoProducto.buscar("ProductoPrueba");

        // Verificar que el producto obtenido no sea nulo y tenga los datos esperados
        assertNotNull(producto);
        assertEquals("ProductoPrueba", producto.getNombre());
        assertEquals("Descripción de prueba", producto.getDescripcion());
        assertEquals("unidad", producto.getUnidadDeMedida());
        assertEquals(10, producto.getStock());
        assertEquals(100.0, producto.getPrecioUnitario(), 0.01);
        assertEquals(5, producto.getLimiteMinimo());
        assertEquals(10, producto.getCantidadDeReposicion());

        // Verificar que se llama a createStatement y executeQuery
        verify(mockConn).createStatement();
        verify(mockStatement).executeQuery(anyString());
    }
}
