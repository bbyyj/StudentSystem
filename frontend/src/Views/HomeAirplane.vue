<template>
  <div id="world"></div>
</template>

<script setup>

import { onMounted, onBeforeUnmount } from 'vue';
import * as THREE from 'three';

const Colors = {
  red: 0xf25346,
  yellow: 0xedeb27,
  white: 0xd8d0d1,
  brown: 0x59332e,
  pink: 0xF5986E,
  brownDark: 0x23190f,
  blue: 0x68c3c0,
  green: 0x458248,
  purple: 0x551A8B,
  lightgreen: 0x629265,
};

let scene, camera, fieldOfView, aspectRatio, nearPlane, farPlane, HEIGHT, WIDTH, renderer, container;

onMounted(() => {
  createScene();
  createLights();
  createWorld();
  createSky();
  createOrbit();
  createSun();
  createForest();
  createPlane();
  loop();
  window.addEventListener('resize', handleWindowResize, false);
});

onBeforeUnmount(() => {
  window.removeEventListener('resize', handleWindowResize, false);
});

function createScene() {
  HEIGHT = window.innerHeight*0.8;
  WIDTH = window.innerWidth*0.85;

  scene = new THREE.Scene();
  scene.fog = new THREE.Fog(0xf7d9aa, 100, 950);

  aspectRatio = WIDTH / HEIGHT;
  fieldOfView = 65;
  nearPlane = 1;
  farPlane = 10000;
  camera = new THREE.PerspectiveCamera(fieldOfView, aspectRatio, nearPlane, farPlane);
  camera.position.x = 0;
  camera.position.y = 150;
  camera.position.z = 90;
  // this.camera = new THREE.PerspectiveCamera(50, width / height, 1, 5000);


  renderer = new THREE.WebGLRenderer({ alpha: true, antialias: true });
  renderer.setSize(WIDTH, HEIGHT);
  renderer.shadowMap.enabled = true;

  container = document.getElementById('world');
  container.appendChild(renderer.domElement);
}

function handleWindowResize() {
  HEIGHT = window.innerHeight;
  WIDTH = window.innerWidth;
  renderer.setSize(WIDTH, HEIGHT);
  camera.aspect = WIDTH / HEIGHT;
  camera.updateProjectionMatrix();
}

let hemisphereLight, shadowLight;
function createLights() {
  hemisphereLight = new THREE.HemisphereLight(0xaaaaaa, 0x000000, 0.9);
  shadowLight = new THREE.DirectionalLight(0xffffff, 0.9);
  shadowLight.position.set(0, 350, 350);
  shadowLight.castShadow = true;

  shadowLight.shadow.camera.left = -650;
  shadowLight.shadow.camera.right = 650;
  shadowLight.shadow.camera.top = 650;
  shadowLight.shadow.camera.bottom = -650;
  shadowLight.shadow.camera.near = 1;
  shadowLight.shadow.camera.far = 1000;

  shadowLight.shadow.mapSize.width = 2048;
  shadowLight.shadow.mapSize.height = 2048;

  scene.add(hemisphereLight);
  scene.add(shadowLight);
}

// Land
class Land {
  constructor() {
    const geom = new THREE.CylinderGeometry(600, 600, 1700, 40, 10);
    geom.applyMatrix4(new THREE.Matrix4().makeRotationX(-Math.PI / 2));

    const mat = new THREE.MeshPhongMaterial({
      color: Colors.lightgreen,
      flatShading: true,
    });

    this.mesh = new THREE.Mesh(geom, mat);
    this.mesh.receiveShadow = true;
  }
}

// Orbit
class Orbit {
  constructor() {
    const geom = new THREE.Object3D();
    this.mesh = geom;
  }
}

// Sun
class Sun {
  constructor() {
    this.mesh = new THREE.Object3D();
    const sunGeom = new THREE.SphereGeometry(400, 20, 10);
    const sunMat = new THREE.MeshPhongMaterial({
      color: 0xffd700, // Yellow
      flatShading: THREE.FlatShading,
    });
    const sun = new THREE.Mesh(sunGeom, sunMat);
    sun.castShadow = false;
    sun.receiveShadow = false;
    this.mesh.add(sun);
  }
}

// Cloud
class Cloud {
  constructor() {
    this.mesh = new THREE.Object3D();
    const geom = new THREE.DodecahedronGeometry(20, 0);
    const mat = new THREE.MeshPhongMaterial({
      color: Colors.white, // White
    });

    const nBlocs = 3 + Math.floor(Math.random() * 3);

    for (let i = 0; i < nBlocs; i++) {
      const m = new THREE.Mesh(geom, mat);
      m.position.x = i * 15;
      m.position.y = Math.random() * 10;
      m.position.z = Math.random() * 10;
      m.rotation.z = Math.random() * Math.PI * 2;
      m.rotation.y = Math.random() * Math.PI * 2;
      const s = 0.1 + Math.random() * 0.9;
      m.scale.set(s, s, s);
      this.mesh.add(m);
    }
  }
}

// Sky
class Sky {
  constructor() {
    this.mesh = new THREE.Object3D();
    this.nClouds = 25;
    const stepAngle = (Math.PI * 2) / this.nClouds;

    for (let i = 0; i < this.nClouds; i++) {
      const c = new Cloud();
      const a = stepAngle * i;
      const h = 800 + Math.random() * 200;
      c.mesh.position.y = Math.sin(a) * h;
      c.mesh.position.x = Math.cos(a) * h;
      c.mesh.rotation.z = a + Math.PI / 2;
      c.mesh.position.z = -400 - Math.random() * 400;
      const s = 1 + Math.random() * 2;
      c.mesh.scale.set(s, s, s);
      this.mesh.add(c.mesh);
    }
  }
}

// Tree
class Tree {
  constructor() {
    this.mesh = new THREE.Object3D();

    const matTreeLeaves = new THREE.MeshPhongMaterial({
      color: Colors.green,
      flatShading: THREE.FlatShading,
    });

    const geomTreeBase = new THREE.BoxGeometry(10, 20, 10);
    const matTreeBase = new THREE.MeshBasicMaterial({ color: Colors.brown });
    const treeBase = new THREE.Mesh(geomTreeBase, matTreeBase);
    treeBase.castShadow = true;
    treeBase.receiveShadow = true;
    this.mesh.add(treeBase);

    const geomTreeLeaves1 = new THREE.CylinderGeometry(1, 12 * 3, 12 * 3, 4);
    const treeLeaves1 = new THREE.Mesh(geomTreeLeaves1, matTreeLeaves);
    treeLeaves1.castShadow = true;
    treeLeaves1.receiveShadow = true;
    treeLeaves1.position.y = 20;
    this.mesh.add(treeLeaves1);

    const geomTreeLeaves2 = new THREE.CylinderGeometry(1, 9 * 3, 9 * 3, 4);
    const treeLeaves2 = new THREE.Mesh(geomTreeLeaves2, matTreeLeaves);
    treeLeaves2.castShadow = true;
    treeLeaves2.position.y = 40;
    treeLeaves2.receiveShadow = true;
    this.mesh.add(treeLeaves2);

    const geomTreeLeaves3 = new THREE.CylinderGeometry(1, 6 * 3, 6 * 3, 4);
    const treeLeaves3 = new THREE.Mesh(geomTreeLeaves3, matTreeLeaves);
    treeLeaves3.castShadow = true;
    treeLeaves3.position.y = 55;
    treeLeaves3.receiveShadow = true;
    this.mesh.add(treeLeaves3);
  }
}

// Flower

class Flower {
  constructor() {
    this.mesh = new THREE.Object3D();

    const geomStem = new THREE.BoxGeometry(5, 50, 5, 1, 1, 1);
    const matStem = new THREE.MeshPhongMaterial({
      color: Colors.green,
      flatShading: THREE.FlatShading,
    });
    const stem = new THREE.Mesh(geomStem, matStem);
    stem.castShadow = false;
    stem.receiveShadow = true;
    this.mesh.add(stem);

    const geomPetalCore = new THREE.BoxGeometry(10, 10, 10, 1, 1, 1);
    const matPetalCore = new THREE.MeshPhongMaterial({
      color: Colors.yellow,
      flatShading: THREE.FlatShading,
    });
    const petalCore = new THREE.Mesh(geomPetalCore, matPetalCore);
    petalCore.castShadow = false;
    petalCore.receiveShadow = true;

    const petalColor = petalColors[Math.floor(Math.random() * 3)];

    const geomPetal = new THREE.BoxGeometry(15, 20, 5, 1, 1, 1);
    const matPetal = new THREE.MeshBasicMaterial({ color: petalColor });
    // geomPetal.vertices[5].y -= 4;
    // geomPetal.vertices[4].y -= 4;
    // geomPetal.vertices[7].y += 4;
    // geomPetal.vertices[6].y += 4;
    // geomPetal.translate(12.5, 0, 3);
    const positionAttribute = geomPetal.getAttribute('position');
    const array = positionAttribute.array;
    array[3 * 5 + 1] -= 4; // vertex 5, y coordinate
    array[3 * 4 + 1] -= 4; // vertex 4, y coordinate
    array[3 * 7 + 1] += 4; // vertex 7, y coordinate
    array[3 * 6 + 1] += 4; // vertex 6, y coordinate

    positionAttribute.needsUpdate = true;
    geomPetal.translate(12.5, 0, 3);


    const petals = [];
    for (let i = 0; i < 4; i++) {
      petals[i] = new THREE.Mesh(geomPetal, matPetal);
      petals[i].rotation.z = i * Math.PI / 2;
      petals[i].castShadow = true;
      petals[i].receiveShadow = true;
    }

    petalCore.add(petals[0], petals[1], petals[2], petals[3]);
    petalCore.position.y = 25;
    petalCore.position.z = 3;
    this.mesh.add(petalCore);
  }
}

const petalColors = [Colors.red, Colors.yellow, Colors.blue];

class Forest {
  constructor() {
    this.mesh = new THREE.Object3D();

    this.nTrees = 300;
    const stepAngle = Math.PI * 2 / this.nTrees;

    for (let i = 0; i < this.nTrees; i++) {
      const t = new Tree();
      const a = stepAngle * i;
      const h = 605;
      t.mesh.position.y = Math.sin(a) * h;
      t.mesh.position.x = Math.cos(a) * h;
      t.mesh.rotation.z = a + (Math.PI / 2) * 3;
      t.mesh.position.z = 0 - Math.random() * 600;
      const s = 0.3 + Math.random() * 0.75;
      t.mesh.scale.set(s, s, s);
      this.mesh.add(t.mesh);
    }

    this.nFlowers = 350;
    const flowerStepAngle = Math.PI * 2 / this.nFlowers;

    for (let i = 0; i < this.nFlowers; i++) {
      const f = new Flower();
      const a = flowerStepAngle * i;
      const h = 605;
      f.mesh.position.y = Math.sin(a) * h;
      f.mesh.position.x = Math.cos(a) * h;
      f.mesh.rotation.z = a + (Math.PI / 2) * 3;
      f.mesh.position.z = 0 - Math.random() * 600;
      const s = 0.1 + Math.random() * 0.3;
      f.mesh.scale.set(s, s, s);
      this.mesh.add(f.mesh);
    }
  }
}

class AirPlane {
  constructor() {
    this.mesh = new THREE.Object3D();

    // Create the cabin
    const geomCockpit = new THREE.BoxGeometry(80, 50, 50);
    const matCockpit = new THREE.MeshPhongMaterial({ color: Colors.red, flatShading: true });
    this.modifyVertices(geomCockpit, [
      { index: 4, y: -10, z: 20 },
      { index: 5, y: -10, z: -20 },
      { index: 6, y: 30, z: 20 },
      { index: 7, y: 30, z: -20 }
    ]);
    const cockpit = new THREE.Mesh(geomCockpit, matCockpit);
    cockpit.castShadow = true;
    cockpit.receiveShadow = true;
    this.mesh.add(cockpit);

    // Create the engine
    const geomEngine = new THREE.BoxGeometry(20, 50, 50);
    const matEngine = new THREE.MeshPhongMaterial({ color: Colors.white, flatShading: true });
    const engine = new THREE.Mesh(geomEngine, matEngine);
    engine.position.x = 40;
    engine.castShadow = true;
    engine.receiveShadow = true;
    this.mesh.add(engine);

    // Create the tail
    const geomTailPlane = new THREE.BoxGeometry(15, 20, 5);
    const matTailPlane = new THREE.MeshPhongMaterial({ color: Colors.red, flatShading: true });
    const tailPlane = new THREE.Mesh(geomTailPlane, matTailPlane);
    tailPlane.position.set(-35, 25, 0);
    tailPlane.castShadow = true;
    tailPlane.receiveShadow = true;
    this.mesh.add(tailPlane);

    // Create the wing
    const geomSideWing = new THREE.BoxGeometry(40, 4, 150);
    const matSideWing = new THREE.MeshPhongMaterial({ color: Colors.red, flatShading: true });

    const sideWingTop = new THREE.Mesh(geomSideWing, matSideWing);
    const sideWingBottom = new THREE.Mesh(geomSideWing, matSideWing);
    sideWingTop.castShadow = true;
    sideWingTop.receiveShadow = true;
    sideWingBottom.castShadow = true;
    sideWingBottom.receiveShadow = true;

    sideWingTop.position.set(20, 12, 0);
    sideWingBottom.position.set(20, -3, 0);
    this.mesh.add(sideWingTop);
    this.mesh.add(sideWingBottom);

    const geomWindshield = new THREE.BoxGeometry(3, 15, 20);
    const matWindshield = new THREE.MeshPhongMaterial({ color: Colors.white, transparent: true, opacity: 0.3, flatShading: true });
    const windshield = new THREE.Mesh(geomWindshield, matWindshield);
    windshield.position.set(5, 27, 0);

    windshield.castShadow = true;
    windshield.receiveShadow = true;

    this.mesh.add(windshield);

    const geomPropeller = new THREE.BoxGeometry(20, 10, 10);
    this.modifyVertices(geomPropeller, [
      { index: 4, y: -5, z: 5 },
      { index: 5, y: -5, z: -5 },
      { index: 6, y: 5, z: 5 },
      { index: 7, y: 5, z: -5 }
    ]);
    const matPropeller = new THREE.MeshPhongMaterial({ color: Colors.brown, flatShading: true });
    this.propeller = new THREE.Mesh(geomPropeller, matPropeller);
    this.propeller.castShadow = true;
    this.propeller.receiveShadow = true;

    const geomBlade1 = new THREE.BoxGeometry(1, 100, 10);
    const geomBlade2 = new THREE.BoxGeometry(1, 10, 100);
    const matBlade = new THREE.MeshPhongMaterial({ color: Colors.brownDark, flatShading: true });

    const blade1 = new THREE.Mesh(geomBlade1, matBlade);
    blade1.position.set(8, 0, 0);
    blade1.castShadow = true;
    blade1.receiveShadow = true;

    const blade2 = new THREE.Mesh(geomBlade2, matBlade);
    blade2.position.set(8, 0, 0);
    blade2.castShadow = true;
    blade2.receiveShadow = true;
    this.propeller.add(blade1, blade2);
    this.propeller.position.set(50, 0, 0);
    this.mesh.add(this.propeller);

    const wheelProtecGeom = new THREE.BoxGeometry(30, 15, 10);
    const wheelProtecMat = new THREE.MeshPhongMaterial({ color: Colors.white, flatShading: true });
    const wheelProtecR = new THREE.Mesh(wheelProtecGeom, wheelProtecMat);
    wheelProtecR.position.set(25, -20, 25);
    this.mesh.add(wheelProtecR);

    const wheelTireGeom = new THREE.BoxGeometry(24, 24, 4);
    const wheelTireMat = new THREE.MeshPhongMaterial({ color: Colors.brownDark, flatShading: true });
    const wheelTireR = new THREE.Mesh(wheelTireGeom, wheelTireMat);
    wheelTireR.position.set(25, -28, 25);

    const wheelAxisGeom = new THREE.BoxGeometry(10, 10, 6);
    const wheelAxisMat = new THREE.MeshPhongMaterial({ color: Colors.brown, flatShading: true });
    const wheelAxis = new THREE.Mesh(wheelAxisGeom, wheelAxisMat);
    wheelTireR.add(wheelAxis);

    this.mesh.add(wheelTireR);

    const wheelProtecL = wheelProtecR.clone();
    wheelProtecL.position.z = -wheelProtecR.position.z;
    this.mesh.add(wheelProtecL);

    const wheelTireL = wheelTireR.clone();
    wheelTireL.position.z = -wheelTireR.position.z;
    this.mesh.add(wheelTireL);

    const wheelTireB = wheelTireR.clone();
    wheelTireB.scale.set(0.5, 0.5, 0.5);
    wheelTireB.position.set(-35, -5, 0);
    this.mesh.add(wheelTireB);

    const suspensionGeom = new THREE.BoxGeometry(4, 20, 4);
    suspensionGeom.applyMatrix4(new THREE.Matrix4().makeTranslation(0, 10, 0));
    const suspensionMat = new THREE.MeshPhongMaterial({ color: Colors.red, flatShading: true });
    const suspension = new THREE.Mesh(suspensionGeom, suspensionMat);
    suspension.position.set(-35, -5, 0);
    suspension.rotation.z = -0.3;
    this.mesh.add(suspension);
  }

  modifyVertices(geometry, modifications) {
    const positionAttribute = geometry.attributes.position;
    modifications.forEach(mod => {
      positionAttribute.setY(mod.index, positionAttribute.getY(mod.index) + mod.y);
      positionAttribute.setZ(mod.index, positionAttribute.getZ(mod.index) + mod.z);
    });
    positionAttribute.needsUpdate = true;
  }
}


let land, sky,orbit,forest,sun,airplane;
const offSet = -600;

function createWorld() {
  land = new Land();
  land.mesh.position.y = offSet;
  scene.add(land.mesh);
}

function createSky(){
  sky = new Sky();
  sky.mesh.position.y = offSet;
  scene.add(sky.mesh);
}
function createOrbit(){
  orbit = new Orbit();
  orbit.mesh.position.y = offSet;
  orbit.mesh.rotation.z = -Math.PI/6;
  scene.add(orbit.mesh);
}
function createSun(){
  sun = new Sun();
  sun.mesh.scale.set(1,1,.3);
  sun.mesh.position.set(0,-30,-850);
  scene.add(sun.mesh);
}
function createForest(){
  forest = new Forest();
  forest.mesh.position.y = offSet;
  scene.add(forest.mesh);
}
function createPlane(){
  airplane = new AirPlane();
  airplane.mesh.scale.set(.35,.35,.35);
  airplane.mesh.position.set(-40,110,-250);
  // airplane.mesh.rotation.z = Math.PI/15;
  scene.add(airplane.mesh);
}

function loop() {
  land.mesh.rotation.z += 0.003;
  orbit.mesh.rotation.z += .001;
  sky.mesh.rotation.z += .003;
  forest.mesh.rotation.z += .005;

  render(scene, camera);
  requestAnimationFrame(loop);
}
function render() {
  renderer.render(scene, camera);
}

</script>


<style scoped>
* {
  margin: 0;
  padding: 0;
}

#world {
  position: absolute;
  width: 85%;
  height: 80%;
  overflow: hidden;
  background: linear-gradient(#e4e0ba, #f7d9aa);
}
</style>