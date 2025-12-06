# Ray Tracing Rendering Engine

A fully-featured **ray tracing engine** capable of generating photorealistic images by simulating the behavior of light. The system supports a wide variety of geometric shapes, lighting models, and optical enhancements such as soft shadows, anti-aliasing, and depth of field. Designed with modularity and extensibility in mind, the engine allows users to construct rich scenes and render them with cinematic visual realism.

---

## 🚀 Features

### 🔷 Geometry Library

The engine includes a comprehensive set of geometric primitives and composite objects:

- **Primitives**: Sphere, Plane, Triangle, Tube, Cylinder, Polygon
- **Composite Objects**: Combined geometries sharing transformations and materials
- Each geometry implements:
    - Ray intersection logic
    - Surface normal computation
    - Material support (diffuse, specular, transparency, reflectivity)

---


### 💡 Advanced Lighting System

Supports multiple light types configured per scene:

- **Point Light** – Emits light in all directions
- **Directional Light** – Infinite light source, sun-like
- **Spotlight** – Controlled cone and attenuation falloff

Illumination includes:

- Diffuse reflection
- Specular highlights
- Reflection & refraction
- Multi-source shadows

---

## 🎨 Visual Enhancements

### ✔ Soft Shadows
Simulates area light sources by sampling multiple shadow rays, producing:

- Realistic penumbra regions
- Natural gradient transitions instead of hard edges

### ✔ Anti-Aliasing
Eliminates jagged edges through supersampling techniques:

- Multiple rays per pixel
- Color averaging for smooth boundaries
- Improved visual clarity along contrast lines

### ✔ Depth of Field
Camera simulation for cinematographic realism:

- Configurable aperture and focal distance
- Multiple lens rays per pixel
- Sharp focal objects with gradual blur elsewhere

---

## 🖥 Rendering Capabilities

The renderer can produce any scene defined by:

- A combination of objects, materials, and light sources
- Customizable camera and view plane parameters
- Adjustable rendering configurations: resolution, sampling, threading, effects

Output is rasterized image files processed **pixel-by-pixel** using ray–geometry recursion.

---

## ⚙ Architecture & Performance

- **Multi-threaded rendering** for efficient computation on modern CPUs
- **Modular design**: plug-and-play geometries, materials, camera models, and effects
- **Deterministic math engine** ensures reproducible output

---

## 🏁 Result

This ray tracer produces images with:

- Realistic reflections and refractions
- Smooth edges and detailed lighting
- Physically accurate shadowing
- Depth-enhanced focus similar to real camera lenses
- Support for complex geometry compositions

It forms a robust foundation for further graphics research, including:

- Global illumination
- Physically based materials
- Caustics and photon mapping
- Procedural textures


---

## 📂 Project Status

The project is functional and continuously evolving. Contributions, optimizations, new geometries, and rendering features are welcome.

---



Happy rendering! 🌟

